pipeline {
    agent any

    tools {
        // S'assurer que le nom correspond à ton instance de Jenkins configurée pour Java 26
        jdk 'java-26' 
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Récupération du code source...'
                checkout scm
            }
        }

        stage('Validation de l\'environnement') {
            steps {
                sh 'java -version'
                sh './mvnw -v'
            }
        }

        stage('Nettoyage & Compilation') {
            steps {
                echo 'Compilation en cours avec pnpm/mvn wrapper...'
                sh './mvnw clean compile'
            }
        }

        stage('Exécution des Tests') {
            steps {
                echo 'Lancement des tests unitaires et d\'intégration...'
                sh './mvnw test'
            }
        }

        stage('Build Package') {
            steps {
                echo 'Génération de l\'artéfact exécutable (JAR)...'
                sh './mvnw package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Le pipeline s\'est exécuté avec succès ! Ton JAR est prêt.'
        }
        failure {
            echo 'Échec du pipeline. Vérifie les logs de compilation ou les rapports de tests.'
        }
    }
}