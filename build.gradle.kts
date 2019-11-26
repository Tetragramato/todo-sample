plugins {
    kotlin("jvm").version("1.3.50")
    kotlin("plugin.spring").version("1.3.50")
    kotlin("plugin.allopen").version("1.3.50")
    id("org.springframework.boot") version "2.1.7.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.adarshr.test-logger") version "1.7.0"
}

group = "com.tetragramato"
version = "0.0.1-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

val junitJupiterVersion = "5.5.1"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configurations {
    testImplementation {
        exclude(group = "junit")
    }
    implementation {
        exclude(module = "kotlin-stdlib-jdk7")
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

    test {
        useJUnitPlatform()
    }
}