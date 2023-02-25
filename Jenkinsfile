pipeline{
    agent { label 'android' }
    stages{
        stage("Unit Test"){
            steps{
                echo("unit test running")
                echo(env.GIT_BRANCH)
                script{
                    sh "./gradlew testDebug"
                }
            }
        }
        stage("build"){
            steps{
                echo("building")
                script{
                    sh "./gradlew :app:assembleDebug"
                }
            }
        }
    }
}