pipeline {
    agent any

    tools {
        allure 'Allure'
    }

    stages {
        stage('Clonar Repositorio') {
            steps {
                checkout scm
            }
        }

        stage('Inicializar Entorno') {
            steps {
                echo 'Limpiando entorno y verificando permisos...'
                // Si corre en Linux/Docker le da permisos al ejecutable
                sh 'chmod +x gradlew' || true
            }
        }

        stage('Ejecutar Pruebas Automatizadas') {
            steps {
                echo 'Iniciando suite de pruebas Cucumber + Selenium en DemoBlaze...'
                script {
                    if (isUnix()) {
                        sh './gradlew test --no-daemon'
                    } else {
                        bat 'gradlew.bat test --no-daemon'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Generando reporte de evidencias en Allure Reports...'
            allure includeProperties: false, 
                   jdk: '', 
                   properties: [], 
                   reportBuildPolicy: 'ALWAYS', 
                   results: [[path: 'build/allure-results']]
        }
    }
}