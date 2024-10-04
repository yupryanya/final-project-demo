pipeline {
    agent any

    tools {
        gradle "Gradle 8.10"
    }

    parameters {
        choice(name: 'TASK',
                        choices: ['test', 'regress', 'smoke'],
                        description: 'Select the task to run')
        choice(name: 'BROWSER',
                        choices: ['chrome', 'firefox'],
                        description: 'Select the browser for testing')
        choice(name: 'BROWSER_VERSION',
                        choices: ['100', '99', '98'],
                        description: 'Select the browser version')
        choice(name: 'RESOLUTION',
                        choices: ['1920x1080', '1280x720'],
                        description: 'Select the screen resolution')
        choice(name: 'TEST_ENV',
                        choices: ['bas','m3'],
                        description: 'Select the test environment')
        choice(name: 'REMOTE_URL',
                        choices: ['selenoid.autotests.cloud'],
                        description: 'Select the url for remote execution')
        string(name: 'COMMENT',
                        defaultValue: '',
                        description: 'Enter a comment for the report')
        credentials(name: 'TELEGRAM_TOKEN',
                        description: 'Telegram bot token for sending notifications in telegram chat',
                        defaultValue: '021-azovceva-telegram-token',
                        credentialType: "jenkins_secret_text_credentials",
                        required: true)
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/yupryanya/bas-demo'
            }
        }
        stage('Test') {
            steps {
                withAllureUpload(name: '${JOB_NAME} - #${BUILD_NUMBER}', projectId: '3838', results: [[path: 'build/allure-results']], serverId: 'allure-server', tags: '') {
                    sh 'gradle clean ${TASK} -DisRemote=true -Dbrowser=${BROWSER} -DbrowserVersion=${BROWSER_VERSION} -DbrowserSize=${RESOLUTION} -Denv=${TEST_ENV} -DremoteUrl=${REMOTE_URL}'
                }
            }
        }
        stage('Prepare Post-Build') {
            steps {
                script {
                    def jarFile = '../allure-notifications-4.7.0.jar'
                    if (!fileExists(file: jarFile)) {
                        sh 'wget https://github.com/qa-guru/allure-notifications/releases/download/4.7.0/allure-notifications-4.7.0.jar -P ..'
                    }
                }
            }
        }
    }

    post {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
            script {
               withCredentials([string(credentialsId: 'TELEGRAM_TOKEN', variable: 'TELEGRAM_TOKEN')]) {
                  writeFile file: 'notifications/config.json', text: """
                                    {
                                      "base": {
                                        "logo": "",
                                        "project": "${JOB_NAME}",
                                        "environment": "${TEST_ENV}",
                                        "comment": "${COMMENT}",
                                        "reportLink": "${BUILD_URL}",
                                        "language": "en",
                                        "allureFolder": "allure-report",
                                        "enableChart": true
                                      },
                                      "telegram": {
                                        "token": "${TELEGRAM_TOKEN}",
                                        "chat": "-1001755259300",
                                        "replyTo": ""
                                      }
                                    }
                                    """
                 }
                sh 'java -DconfigFile=notifications/config.json -jar ../allure-notifications-4.7.0.jar'
            }
        }
    }
}