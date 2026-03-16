pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk   'JDK-11'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: '[github.com](https://github.com/your-org/ecommerce-automation.git)'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=testng.xml'
            }
        }

        stage('Publish Extent Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Test Report'
                ])
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/*.html, logs/*.log',
                             allowEmptyArchive: true
        }
        failure {
            mail to: 'qa-team@company.com',
                 subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "Check Jenkins: ${env.BUILD_URL}"
        }
    }
}
