#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        // script contains all information about environment, pipeline, etc.
        this.script = script
    }

    def buildDockerImage(String imageName, String ipWithPort) {
        script.echo "building the docker image..."
        script.sh "docker build -t $ipWithPort/$imageName ."
    }

    def dockerLogin(String credentialsKey, String ipWithPort) {
        script.withCredentials([script.usernamePassword(credentialsId: credentialsKey, passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            // usage of variables here instead of $Pass and $USER
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin $ipWithPort"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push 64.226.110.153:8083/$imageName"
    }

    def buildDockerImageECR(String credentialsKey, String imageName, String ipWithPort) {
        script.withCredentials([script.aws(credentialsId: credentialsKey)]){
            script.sh "docker build -t $ipWithPort/$imageName ."
            script.sh "aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin $ipWithPort"
            script.sh "docker push $ipWithPort/$imageName"
        }
    }
}