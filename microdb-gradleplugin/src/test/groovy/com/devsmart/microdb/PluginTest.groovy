package com.devsmart.microdb;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

public class PluginTest {

    @Test
    public void applyJavaProjectTest() {
        Project project = ProjectBuilder.builder().build();
        project.with {
            apply plugin: 'java'
            apply plugin: 'com.devsmart.microdb'

        }

        project.evaluate();

        assertNotNull(project.getTasks().findByName("generateMicrodb"));
    }


    /*
    @Test
    public void applyNewAndroidAppProjectTest() {

        //Note this test should work if the env var ANDROID_HOME is defined. If not, manual set by uncommenting below:

        //System.setProperty("android.home", "/home/paul/apps/android-sdk-linux")
        //System.properties.setProperty("android.home", "/home/paul/apps/android-sdk-linux")
        Project project = ProjectBuilder.builder().build();
        project.with {
            buildscript {
                repositories {
                    jcenter()
                }
                dependencies {
                    classpath 'com.android.tools.build:gradle:2.1.2'
                }
            }
            apply plugin: 'com.android.application'
            apply plugin: 'com.devsmart.microdb'

            android {
                compileSdkVersion 19
                buildToolsVersion "20.0.0"

                defaultConfig {
                    applicationId "com.devsmart.microdb.test"
                    minSdkVersion 17
                    targetSdkVersion 19
                    versionCode 1
                    versionName '0.0.1'
                }
            }
        }

        project.evaluate()

        //project.getPluginManager().apply("com.android.application");


        //project.getPluginManager().apply("com.devsmart.microdb");


        //assertNotNull(project.getTasks().findByName("generateMicroDBdebugSources"));
    }

    @Test
    public void applyOldAndroidAppProjectTest() {

        //Note this test should work if the env var ANDROID_HOME is defined. If not, manual set by uncommenting below:

        //System.setProperty("android.home", "/home/paul/apps/android-sdk-linux")
        //System.properties.setProperty("android.home", "/home/paul/apps/android-sdk-linux")
        Project project = ProjectBuilder.builder().build();
        project.with {
            buildscript {
                repositories {
                    jcenter()
                }
                dependencies {
                    classpath 'com.android.tools.build:gradle:1.5.0'
                }
            }
            apply plugin: 'com.android.application'
            apply plugin: 'com.devsmart.microdb'

            android {
                compileSdkVersion 19
                buildToolsVersion "20.0.0"

                defaultConfig {
                    applicationId "com.devsmart.microdb.test"
                    minSdkVersion 17
                    targetSdkVersion 19
                    versionCode 1
                    versionName '0.0.1'
                }
            }
        }

        project.evaluate()

        //project.getPluginManager().apply("com.android.application");


        //project.getPluginManager().apply("com.devsmart.microdb");


        //assertNotNull(project.getTasks().findByName("generateMicroDBdebugSources"));
    }
    */

}
