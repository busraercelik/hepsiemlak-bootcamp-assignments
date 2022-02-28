# Using jdk as base image
FROM openjdk:8-jdk-alpine

# install dependencies
RUN wget -O activemq.tar.gz http://archive.apache.org/dist/activemq/5.15.6/apache-activemq-5.15.6-bin.tar.gz
RUN tar -xzf activemq.tar.gz

# define the port number the container should expose
#EXPOSE 5000

# Start up the activemq server
CMD ["apache-activemq-5.15.6/bin/activemq", "console"]