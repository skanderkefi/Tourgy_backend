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
                    docker build --no-cache -t tourgy:latest .
                    docker tag tourgy:latest skander99/tourgy:latest
                    docker push skander99/tourgy:latest
					docker rmi tourgy:latest
                '''}}
        	 stage('Update UAT container') {
            when { branch "master" }
            steps {
                sh '''
		     docker login -u "skander99" -p "skander99"
                    docker pull skander99/tourgy:latest                          
                    docker run -p 9200:9200 --name tourgy --network dbconnexion --restart=always -t -d skander99/tourgy:latest
                    docker rmi -f $(docker images -q --filter dangling=true)
                '''
            }
        }
		
	}
  }
