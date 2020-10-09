pca <- function(eData, printDropped = TRUE, scale = TRUE, center = TRUE) 
{
  if (class(eData) == "ExpressionSet") {
    annotationData <- annotation(eData)
    phenoData <- phenoData(eData)
    exprsData <- exprs(eData)
    expressionData <- list(annotationData, phenoData)
  }
  else {
    exprsData <- as.matrix(eData)
    expressionData <- NA
  }
  for (i in 1:length(exprsData[, 1])) {
    if (var(exprsData[i, ]) == 0) {
      exprsData[i, ] <- NA
      if (printDropped) {
        print(paste("Dropped variable", rownames(exprsData)[i], 
                    "because scaling is not possible (all values are equal)"))
      }
    }
  }
  exprsData <- na.omit(exprsData)
  p <- prcomp(t(exprsData), scale = scale, center = center)
  res <- list(scores = p$x, loadings = p$rotation, pov = p$sdev^2/sum(p$sdev^2), 
              expressionData = expressionData)
  class(res) <- "pca"
  res
}

pca_func <- function(data, groups, title, print_ellipse = TRUE) {
  
  # perform pca and extract scores
  pcaOutput <- pca(data, printDropped = FALSE, scale = TRUE, center = TRUE)
  pcaOutput2 <- as.data.frame(pcaOutput$scores)
  
  # define groups for plotting
  pcaOutput2$groups <- groups %>%
    stringr::str_replace_all(pattern = "_", replacement = " ") %>%
    tolower() %>%
    stringi::stri_trans_totitle()
  
  
  # when plotting samples calculate ellipses for plotting (when plotting features, there are no replicates)
  if (print_ellipse) {
    
    centroids <- aggregate(cbind(PC1, PC2) ~ groups, pcaOutput2, mean)
    conf.rgn  <- do.call(rbind, lapply(unique(pcaOutput2$groups), function(t)
      data.frame(groups = as.character(t),
                 ellipse::ellipse(cov(pcaOutput2[pcaOutput2$groups == t, 1:2]),
                         centre = as.matrix(centroids[centroids$groups == t, 2:3]),
                         level = 0.95),
                 stringsAsFactors = FALSE)))
    
    plot <- ggplot(data = pcaOutput2, aes(x = PC1, y = PC2, group = groups, color = groups)) + 
      geom_polygon(data = conf.rgn, aes(fill = groups), alpha = 0.2) +
      geom_point(size = 2, alpha = 0.4) + 
      scale_color_brewer(palette = "Set1") +
      labs(title = title,
           color = "",
           fill = "",
           x = paste0("PC1: ", round(pcaOutput$pov[1], digits = 2) * 100, "% variance"),
           y = paste0("PC2: ", round(pcaOutput$pov[2], digits = 2) * 100, "% variance")) +
      theme(
        axis.text = element_text(size = 12),
        axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
        axis.title = element_text(size = 14),
        panel.grid.major = element_line(color = "grey"),
        panel.grid.minor = element_blank(),
        panel.background = element_rect(fill = "aliceblue"),
        strip.background = element_rect(fill = "navy", color = "navy", size = 1),
        strip.text = element_text(face = "bold", size = 12, color = "white"),
        legend.justification = "top", 
        legend.background = element_blank(),
        panel.border = element_rect(color = "grey", fill = NA, size = 0.5)
      ) + 
      theme(legend.background = element_rect(size = 0.1), 
            legend.position = "bottom", legend.direction = "vertical")
    
  } else {
    
    # if there are fewer than 10 groups (e.g. the predictor classes) I want to have colors from RColorBrewer
    if (length(unique(pcaOutput2$groups)) <= 10) {
      
      plot <- ggplot(data = pcaOutput2, aes(x = PC1, y = PC2, group = groups, color = groups)) + 
        geom_point(size = 3, alpha = 0.6) + 
        scale_color_brewer(palette = "Set1") +
        labs(title = title,
             color = "",
             fill = "",
             x = paste0("PC1: ", round(pcaOutput$pov[1], digits = 2) * 100, "% variance"),
             y = paste0("PC2: ", round(pcaOutput$pov[2], digits = 2) * 100, "% variance"))  +
        theme(
          axis.text = element_text(size = 12),
          axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
          axis.title = element_text(size = 14),
          panel.grid.major = element_line(color = "grey"),
          panel.grid.minor = element_blank(),
          panel.background = element_rect(fill = "aliceblue"),
          strip.background = element_rect(fill = "navy", color = "navy", size = 1),
          strip.text = element_text(face = "bold", size = 12, color = "white"),
          legend.justification = "top", 
          legend.background = element_blank(),
          panel.border = element_rect(color = "grey", fill = NA, size = 0.5)
        ) + 
        theme(legend.background = element_rect(size = 0.1), 
              legend.position = "bottom", legend.direction = "vertical")
      
    } else {
      
      # otherwise use the default rainbow colors
      plot <- ggplot(data = pcaOutput2, aes(x = PC1, y = PC2, group = groups, color = groups)) + 
        geom_point(size = 3, alpha = 0.6) + 
        labs(title = title,
             color = "",
             fill = "",
             x = paste0("PC1: ", round(pcaOutput$pov[1], digits = 2) * 100, "% variance"),
             y = paste0("PC2: ", round(pcaOutput$pov[2], digits = 2) * 100, "% variance")) +
        theme(
          axis.text = element_text(size = 12),
          axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
          axis.title = element_text(size = 14),
          panel.grid.major = element_line(color = "grey"),
          panel.grid.minor = element_blank(),
          panel.background = element_rect(fill = "aliceblue"),
          strip.background = element_rect(fill = "navy", color = "navy", size = 1),
          strip.text = element_text(face = "bold", size = 12, color = "white"),
          legend.justification = "top", 
          legend.background = element_blank(),
          panel.border = element_rect(color = "grey", fill = NA, size = 0.5)
        ) + 
        theme(legend.background = element_rect(size = 0.1), 
              legend.position = "bottom", legend.direction = "vertical")
      
    } 
  } 
  
  
  
  return(plot)
  
}

############################################################
#                                                          #
#                        My Theme                          ####
#                                                          #
############################################################


my_theme <- function(base_size = 12, base_family = "sans"){
  theme_minimal(base_size = base_size, base_family = base_family) +
    theme(
      axis.text = element_text(size = 12),
      axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
      axis.title = element_text(size = 14),
      panel.grid.major = element_line(color = "grey"),
      panel.grid.minor = element_blank(),
      panel.background = element_rect(fill = "aliceblue"),
      strip.background = element_rect(fill = "navy", color = "navy", size = 1),
      strip.text = element_text(face = "bold", size = 12, color = "white"),
      legend.position = "right",
      legend.justification = "top", 
      legend.background = element_blank(),
      panel.border = element_rect(color = "grey", fill = NA, size = 0.5)
    )
}

theme_set(my_theme())



############################################################
#                                                          #
#             Function To Make a Dummy Matrix              #
#                                                          #
############################################################




dummy <- function(df) {  
  
  NUM <- function(dataframe)dataframe[,sapply(dataframe,is.numeric)]
  FAC <- function(dataframe)dataframe[,sapply(dataframe,is.factor)]
  
  require(ade4)
  if (is.null(ncol(NUM(df)))) {
    DF <- data.frame(NUM(df), acm.disjonctif(FAC(df)))
    names(DF)[1] <- colnames(df)[which(sapply(df, is.numeric))]
  } else {
    DF <- data.frame(NUM(df), acm.disjonctif(FAC(df)))
  }
  return(DF)
} 



############################################################
#                                                          #
#                         To Title                         #
#                                                          #
############################################################
to_title <- function(string){
  string %>%
    stringr::str_replace_all(pattern = "_", replacement = " ") %>%
    tolower() %>%
    stringi::stri_trans_totitle()
}

kmeans_generator <- function(dataframe, num_of_clusters){
  
  
  ############################################################
  #                                                          #
  #          Pulls All Numeric & Integer Variables           #
  #                                                          #
  ############################################################
  
  data = dataframe 
  
  nums_df <- sapply(dataframe, is.numeric)
  dataframe <- dataframe[,nums_df]
  
  
  ############################################################
  #                                                          #
  #           PACKAGE REQUIREMENTS VERIFICATIONS             #
  #                                                          #
  ############################################################
  
  
  if (!require(dplyr)) {
    install.packages("dplyr", repos = "http://cran.us.r-project.org")
    require(dplyr)
  }  
  if (!require(ggplot2)) {
    install.packages("magrittr", repos = "http://cran.us.r-project.org")
    require(magrittr)
  } 
  if (!require(viridis)) {
    install.packages("viridis", repos = "http://cran.us.r-project.org")
    require(viridis)
  } 
  if (!require(cluster)) {
    install.packages("cluster", repos = "http://cran.us.r-project.org")
    require(cluster)
  }
  if (!require(stringr)) {
    install.packages("stringr", repos = "http://cran.us.r-project.org")
    require(stringr)
  }
  if (!require(stringi)) {
    install.packages("stringi", repos = "http://cran.us.r-project.org")
    require(stringi)
  } 
  if (!require(gridExtra)) {
    install.packages("gridExtra", repos = "http://cran.us.r-project.org")
    require(gridExtra)
  } 
  
  ############################################################
  #                                                          #
  #                         To Title                         #
  #                                                          #
  ############################################################
  to_title <- function(string){
    string %>%
      stringr::str_replace_all(pattern = "_", replacement = " ") %>%
      tolower() %>%
      stringi::stri_trans_totitle()
  }
  ############################################################
  #                                                          #
  #                        My Theme                          ####
  #                                                          #
  ############################################################
  
  
  my_theme <- function(base_size = 12, base_family = "sans"){
    theme_minimal(base_size = base_size, base_family = base_family) +
      theme(
        axis.text = element_text(size = 12),
        axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
        axis.title = element_text(size = 14),
        panel.grid.major = element_line(color = "grey"),
        panel.grid.minor = element_blank(),
        panel.background = element_rect(fill = "aliceblue"),
        strip.background = element_rect(fill = "navy", color = "navy", size = 1),
        strip.text = element_text(face = "bold", size = 12, color = "white"),
        legend.position = "right",
        legend.justification = "top", 
        legend.background = element_blank(),
        panel.border = element_rect(color = "grey", fill = NA, size = 0.5)
      )
  }
  
  theme_set(my_theme())
  
  ############################################################
  #                                                          #
  #                       Kmeans Knee                        #
  #                                                          #
  ############################################################
  
  kmeans_CL <- function(dataframe){
    library(viridis)
    mydata = dataframe 
    wss <- (nrow(mydata)-1)*sum(apply(mydata,2,var))
    for (i in 2:15) wss[i] <- sum(kmeans(mydata,
                                         centers=i)$withinss)
    CL <- data.frame(1:15, wss)
    names(CL) <- c("NUMBER_OF_CLUSTERS","WITHING_GROUP_SUM_OF_SQUARES")
    
    CL$deriv[1] <- 0
    for(i in 2:nrow(CL)){
      CL$deriv[i-1] <- abs(CL$WITHING_GROUP_SUM_OF_SQUARES[i-1] -
                             CL$WITHING_GROUP_SUM_OF_SQUARES[i])
    }
    CL$change_in_deriv = 0
    
    for(i in 2:nrow(CL)){
      CL$change_in_deriv[i-1] <- abs(CL$deriv[i-1] - CL$deriv[i])
    }
    return(CL)
  }
  kmeans_knee <- function(dataframe){
    CL = kmeans_CL(dataframe)
    
    ## Illustration
    ggplot(CL, aes(x=NUMBER_OF_CLUSTERS, WITHING_GROUP_SUM_OF_SQUARES, colour=deriv)) +
      geom_path(size=2) +
      scale_color_viridis(guide = guide_colorbar(direction = "horizontal",
                                                 title.position = "left",
                                                 label.position = "bottom",
                                                 label.theme = element_text(angle = 90),
                                                 label.hjust = 0.5,
                                                 label.vjust = 0.5)
      ) +
      geom_point(size=3, alpha = 0.6) +
      scale_x_continuous(breaks=round(seq(min(CL$NUMBER_OF_CLUSTERS),
                                          max(CL$NUMBER_OF_CLUSTERS),
                                          by = 1),1)
      ) +
      labs(x = "NUMBER_OF_CLUSTERS" %>% to_title(),
           y= "WITHING_GROUP_SUM_OF_SQUARES" %>% to_title(),
           title = "K-Means Clustering Approximation",
           color = "WSS \nChange") +
      theme(plot.title = element_text(face="bold", hjust = 0.5),
            legend.background = element_rect(size = 0.1), 
            legend.position = "bottom",
            legend.direction = "horizontal",
            legend.title = element_text(face = "bold"))
  }
  
  
  ############################################################
  #                                                          #
  #                    K-Means Iterations                    #
  #                                                          #
  ############################################################
  
  
  
  
  km_1 <- kmeans(dataframe, num_of_clusters)
  km_1_num <- km_1$cluster %>% max()
  km_1_plot <- ggplot2::autoplot(kmeans(dataframe, num_of_clusters), data, frame = TRUE) + 
    labs(title = sprintf("K-Means Clustering: %d Clusters", km_1_num),
         x="",
         y="PC2",
         color = "Clusters",
         fill = "") +
    guides(fill = FALSE) +
    theme(plot.title = element_text(face="italic", hjust = 0.5),
          legend.background = element_rect(size = 0.1), 
          legend.position = "bottom",
          legend.direction = "horizontal",
          legend.title = element_text(face = "bold"))
  km_2 <- kmeans(dataframe, (num_of_clusters + 1))
  km_2_num <- km_2$cluster %>% max()
  km_2_plot <- ggplot2::autoplot(kmeans(dataframe, num_of_clusters + 1), data, frame = TRUE) + 
    labs(title = sprintf("K-Means Clustering: %d Clusters", km_2_num),
         x="",
         y="",
         color = "Clusters",
         fill = "") +
    guides(fill = FALSE) +
    theme(plot.title = element_text(face="italic", hjust = 0.5),
          legend.background = element_rect(size = 0.1), 
          legend.position = "bottom",
          legend.direction = "horizontal",
          legend.title = element_text(face = "bold"))
  km_3 <- kmeans(dataframe, num_of_clusters + 2)
  km_3_num <- km_3$cluster %>% max()
  km_3_plot <- ggplot2::autoplot(kmeans(dataframe, num_of_clusters + 2), data, frame = TRUE) + 
    labs(title = sprintf("K-Means Clustering: %d Clusters", km_3_num),
         x="PC1",
         y="",
         color = "Clusters",
         fill = "") +
    guides(fill = FALSE) +
    theme(plot.title = element_text(face="italic", hjust = 0.5),
          legend.background = element_rect(size = 0.1), 
          legend.position = "bottom",
          legend.direction = "horizontal",
          legend.title = element_text(face = "bold"))
  main=textGrob("K-Means Clustering Possibilities",gp=gpar(fontsize=20,font=3))
  cap=textGrob(
    sprintf("Optimal Number of Clusters For This Dataset: %s, %s, or %s",
            # Row's with The Highest Change in WSS
            # Highest
            kmeans_CL(dataframe) %>%
              arrange(desc(change_in_deriv)) %>%
              head(3) %>%
              select(NUMBER_OF_CLUSTERS) %>%
              filter(row_number() == 1) %>%
              .$NUMBER_OF_CLUSTERS + 1,
            # 2nd Highest
            kmeans_CL(dataframe) %>%
              arrange(desc(change_in_deriv)) %>%
              head(3) %>%
              select(NUMBER_OF_CLUSTERS) %>%
              filter(row_number() == 2) %>%
              .$NUMBER_OF_CLUSTERS + 1,
            # 3rd Highest
            kmeans_CL(dataframe) %>%
              arrange(desc(change_in_deriv)) %>%
              head(3) %>%
              select(NUMBER_OF_CLUSTERS) %>%
              filter(row_number() == 3) %>%
              .$NUMBER_OF_CLUSTERS + 1
    ), gp=gpar(fontsize=12,font=3)
  )
  grid.arrange(kmeans_knee(dataframe), km_1_plot, km_2_plot, km_3_plot, ncol = 2,nrow=2, top = main,
               bottom = cap)
}

############################################################
#                                                          #
#                        K MEANS CL                        #
#                                                          #
############################################################


kmeans_CL <- function(dataframe){
  library(viridis)
  mydata = dataframe 
  wss <- (nrow(mydata)-1)*sum(apply(mydata,2,var))
  for (i in 2:15) wss[i] <- sum(kmeans(mydata,
                                       centers=i)$withinss)
  CL <- data.frame(1:15, wss)
  names(CL) <- c("NUMBER_OF_CLUSTERS","WITHING_GROUP_SUM_OF_SQUARES")
  
  CL$deriv[1] <- 0
  for(i in 2:nrow(CL)){
    CL$deriv[i-1] <- abs(CL$WITHING_GROUP_SUM_OF_SQUARES[i-1] -
                           CL$WITHING_GROUP_SUM_OF_SQUARES[i])
  }
  CL$change_in_deriv = 0
  for(i in 2:nrow(CL)){
    CL$change_in_deriv[i-1] <- abs(CL$deriv[i - 1] - CL$deriv[i])
  }
  return(CL)
}

############################################################
#                                                          #
#                       K MEANS KNEE                       #
#                                                          #
############################################################



kmeans_knee <- function(dataframe){
  CL = kmeans_CL(dataframe)
  
  ## Illustration
  ggplot(CL, aes(x=NUMBER_OF_CLUSTERS, WITHING_GROUP_SUM_OF_SQUARES, colour=deriv)) +
    geom_path(size=2) +
    scale_color_viridis(guide = guide_colorbar(direction = "horizontal",
                                               title.position = "left",
                                               label.position = "bottom",
                                               label.theme = element_text(angle = 90),
                                               label.hjust = 0.5,
                                               label.vjust = 0.5)
    ) +
    geom_point(size=3, alpha = 0.6) +
    scale_x_continuous(breaks=round(seq(min(CL$NUMBER_OF_CLUSTERS),
                                        max(CL$NUMBER_OF_CLUSTERS),
                                        by = 1),1)
    ) +
    labs(x = "NUMBER_OF_CLUSTERS" %>% to_title(),
         y = "WITHING_GROUP_SUM_OF_SQUARES" %>% to_title(),
         title = "K-Means Clustering Approximation",
         color = "WSS \nChange") +
    theme(plot.title = element_text(face="bold", hjust = 0.5),
          legend.background = element_rect(size = 0.1), 
          legend.position = "bottom",
          legend.direction = "horizontal",
          legend.title = element_text(face = "bold"))
}

############################################################
#                                                          #
#                          NOT IN                          #
#                                                          #
############################################################


'%!in%' <- function(x,y)!('%in%'(x,y))

############################################################
#                                                          #
#                      DETACH PACKAGE                      #
#                                                          #
############################################################


detach_package <- function(pkg, character.only = FALSE)
{
if(!character.only)
  {
    pkg <- deparse(substitute(pkg))
  }
  search_item <- paste("package", pkg, sep = ":")
  while(search_item %in% search())
  {
    detach(search_item, unload = TRUE, character.only = TRUE)
  }
}


detachAllPackages <- function() {
  
  basic.packages <- c("package:stats","package:graphics","package:grDevices","package:utils","package:datasets","package:methods","package:base")
  
  package.list <- search()[ifelse(unlist(gregexpr("package:",search()))==1,TRUE,FALSE)]
  
  package.list <- setdiff(package.list,basic.packages)
  
  if (length(package.list)>0)  for (package in package.list) detach(package, character.only=TRUE)
  
}




############################################################
#                                                          #
#                       REMOVE ROWS                        #
#                                                          #
############################################################



removeRows <- function(rowNum, data) {
  newData <- data[-rowNum, , drop = FALSE]
  rownames(newData) <- NULL
  newData
}


############################################################
#                                                          #
#              Feature Importance With Caret               ####
#                                                          #
############################################################





ggplotimportance <- function(VarImpObject, title ,method, repeat_num, cv_times){
  require(ggplot2)
  require(stringr)
  require(RColorBrewer)
  VarImpObject = importance
  importance_df_1 <- importance$importance
  importance_df_1$group <- rownames(importance_df_1)
  #importance_df_1$group <- str_replace_all(importance_df_1$group,"`", "")
  f = importance_df_1[order(importance_df_1$Overall, decreasing = FALSE), "group"]
  importance_df_2 <- importance_df_1
  importance_df_2$Overall <- 0
  importance_df <- rbind(importance_df_1, importance_df_2)
  # setting factor levels
  importance_df <- within(importance_df, group <- factor(group, levels = f))
  importance_df_1 <- within(importance_df_1, group <- factor(group, levels = f))
  ggplot() +
    geom_point(data = importance_df_1, aes(x = Overall, y = to_title(group), color = to_title(group)), size = 2) +
    geom_path(data = importance_df, aes(x = Overall, y = to_title(group), color = to_title(group), group = to_title(group)), size = 1) +
    scale_color_manual(values = rep(brewer.pal(3, "Set1")[1], 15)) +
    theme(legend.position = "none",
          axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5)) +
    my_theme() +
    labs(
      x = "Importance",
      y = "",
      title = sprintf("%s", title),
      subtitle = "Scaled feature importance",
      caption = sprintf("\nDetermined with %s and
    repeated cross validation (%d repeats, %d times)", method,repeat_num, cv_times)
    ) 
}


############################################################
#                                                          #
#                      TALLY PERCENT                       #
#                                                          #
############################################################
# tally_percent <- function(dataframe, variable){
#   dataframe %>%
#     group_by(eval(parse(text = "variable"))) %>%
#       tally() %>%
#         arrange(desc(n)) %>%
#           mutate(SUM = sum(n)) %>%
#             mutate(PERCENT = signif(((.$n/.$SUM)*100),3)) %>%
#               select(-SUM)
# }


############################################################
#                                                          #
#                 ALTERNATIVE GGPLOT THEME                 #
#                                                          #
############################################################

my_theme_alt <- function(base_size = 8, base_family = "sans"){
  theme_minimal(base_size = base_size, base_family = base_family) +
    theme(
      axis.text = element_text(size =7),
      axis.text.x = element_text(angle = 0, vjust = 0.5, hjust = 0.5),
      axis.title = element_text(size = 11),
      panel.grid.major = element_line(color = "grey"),
      panel.grid.minor = element_blank(),
      panel.background = element_rect(fill = "#fffece"),
      strip.background = element_rect(fill = "#ffb20c", color = "black", size =0.5),
      strip.text = element_text(face = "bold", size = 8, color = "black"),
      legend.position = "bottom",
      legend.justification = "center",
      legend.background = element_blank(),
      panel.border = element_rect(color = "grey30", fill = NA, size = 0.5)
    )
}