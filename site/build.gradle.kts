import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
    alias(libs.plugins.serialization.plugin)
}

group = "com.hopcape.newssaas.admin"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
            head.add {
                link(
                    rel = "preconnect",
                    href = "https://fonts.googleapis.com"
                )
                link(
                    rel = "preconnect",
                    href = "https://fonts.gstatic.com"
                )
                link(
                    rel = "stylesheet",
                    href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap"
                )
            }
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("admin" /*, includeServer = true*/)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                // This default template uses built-in SVG icons, but what's available is limited.
                // Uncomment the following if you want access to a large set of font-awesome icons:
                // implementation(libs.silk.icons.fa)
                implementation(libs.kobwebx.markdown)
                implementation(libs.kotlinx.serialization)
               // implementation(libs.ktor.core)
              //  implementation(libs.ktor.cio)
            }
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        val jvmMain by getting {
//            dependencies {
//                implementation(libs.kobweb.api)
//            }
//        }
    }
}
