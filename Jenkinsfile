pipeline {
    agent any

    tools {
        // Enlaza tu Allure ya configurado y funcional
        allure 'Allure'
    }

    stages {
        stage('Clonar Repositorio') {
            steps {
                // Jenkins clona usando tus campos de la interfaz gráfica
                checkout scm
            }
        }

        stage('Inicializar Entorno') {
            steps {
                echo 'Limpiando entorno y preparando variables para Windows...'
                // Eliminamos el sh 'chmod' conflictivo ya que estás en Windows nativo
            }
        }

        stage('Ejecutar Pruebas Automatizadas') {
            steps {
                echo 'Iniciando suite de pruebas Cucumber + Selenium en DemoBlaze...'
                // Ejecución directa para tu entorno Windows usando el archivo por lotes .bat
                bat 'gradlew.bat test --no-daemon'
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