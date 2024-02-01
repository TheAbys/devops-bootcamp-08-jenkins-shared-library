#!/user/bin/env groovy

def call(String username, String ipAddress, String imageName, String sshAgentCredentials = "ssh-agent-credentials") {
    sshagent(["${sshAgentCredentials}"]) {
        sh "scp server-cmds.sh ${username}@${ipAddress}:/home/ec2-user"
        sh "scp docker-compose.yaml ${username}@${ipAddress}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${username}@${ipAddress} bash ./server-cmds.sh ${imageName}"
    }
}