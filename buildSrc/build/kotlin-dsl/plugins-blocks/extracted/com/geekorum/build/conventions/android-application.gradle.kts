package com.geekorum.build.conventions;   
                                                                      
  
                                                          
  
                                  
  
                                                                    
                                                                       
                                                                    
                                      
  
                                                               
                                                                 
                                                                
                                               
  
                                                                    
                                                                     
   
                                      

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.geekorum.build.android-signing")
    id("com.geekorum.build.android-release-universal-apk")
    id("com.geekorum.build.play-store-publish")
    id("com.geekorum.build.source-license-checker")
}