#!/user/bin/env groovy

def deploy(String username, String ipAddress, String command, String sshAgentCredentials = "ssh-agent-credentials") {
    sshagent([$sshAgentCredentials]) {
        sh "ssh -o StrictHostKeyChecking=no ${username}@${ipAddress} ${command}"
    }
}