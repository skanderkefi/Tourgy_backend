#!/usr/bin/env groovy

pipeline {
    agent any
    options { disableConcurrentBuilds() }
    stages {
    	 stage('Permissions') {
            steps {
                sh 'chmod 775 *' } }
         stage('Compile stage') {
            steps {
                sh "mvn clean compile" }}
	 stage('install') {
             steps {
                sh "mvn install -Dmaven.test.skip=true -P prod"
     		   }}
     		   stage('Update Docker UAT image') {
            when { branch "master" }
            steps {
                sh '''
		     docker login -u "skander99" -p "skander99"
                    docker build --no-cache -t client1:latest .
                    docker tag client1:latest skander99/client1:latest
                    docker push skander99/client1:latest
					docker rmi client1:latest
                '''}}
        	 stage('Update UAT container') {
            when { branch "master" }
            steps {
                sh '''
		     docker login -u "skander99" -p "skander99"
                    docker pull skander99/client1:latest 
                    docker stop client1 
                    docker rm client1                   
                    docker run -p 9003:9003 --name client1 --network dbconnexion --restart=always -t -d skander99/client1:latest
                    docker rmi -f $(docker images -q --filter dangling=true)
                '''
            }
        }
		
	}
  }
