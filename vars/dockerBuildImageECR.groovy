#!/user/bin/env groovy

import com.example.Docker
def call(String credentialsId, String imageName, String ipWithPort) {
    return new Docker(this).buildDockerImageECR(credentialsId, imageName, ipWithPort)
}