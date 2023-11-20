#!/user/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        // script contains all information about environment, pipeline, etc.
        this.script = script
    }

    def buildDockerImage(String imageName) {
        script.echo "building the docker image..."
        script.sh "docker build -t 64.226.110.153:8083/$imageName ."
    }

    def dockerLogin() {
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            // usage of variables here instead of $Pass and $USER
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin 64.226.110.153:8083"
        }
    }

    def dockerPush(String imageName) {
        script.sh "docker push 64.226.110.153:8083/$imageName"
    }
}