plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.22.2")


    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.rest-assured:rest-assured:6.0.1")

}
tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("apiTests"){
    useJUnitPlatform{
        includeTags("api")
    }
}