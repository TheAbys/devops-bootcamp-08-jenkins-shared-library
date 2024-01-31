#!/user/bin/env groovy

def call(String username, String ipAddress, String command, String sshAgentCredentials = "ssh-agent-credentials") {
    sshagent([${sshAgentCredentials}]) {
        sh "scp docker-compose.yaml ${username}@${ipAddress}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${username}@${ipAddress} ${command}"
    }
}