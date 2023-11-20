#!/user/bin/env groovy

def call() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: "nexus-docker-repo", passwordVariable: "PASS", usernameVariable: "USER")]){
        sh 'docker build -t 64.226.110.153:8083/java-maven-app:1.2 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin 64.226.110.153:8083'
        sh 'docker push 64.226.110.153:8083/java-maven-app:1.2'
    }
}