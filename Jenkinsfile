pipeline {
    agent any

    tools {
        jdk 'java-21' 
        maven 'maven-3.9' // On ajoute aussi l'outil Maven global
    }

    stages {
        stage('Validation de l\'environnement') {
            steps {
                sh 'java -version'
                sh 'mvn -v'
            }
        }

        stage('Nettoyage & Compilation') {
            steps {
                echo 'Compilation en cours...'
                sh 'mvn clean compile'
            }
        }

        stage('Exécution des Tests') {
            steps {
                echo 'Lancement des tests unitaires...'
                sh 'mvn test'
            }
        }

        stage('Build Package') {
            steps {
                echo 'Génération de l\'artéfact exécutable (JAR)...'
                sh 'mvn package -DskipTests'
            }
        }
    }
}