Codetest
========

## Install #####

Being a Maven project it should be easy enough to build and deploy. The project uses port 8080, so make sure that port isn't being used elsewhere.

Source code are located in src/main/java/hello, tests are located in src/test/java.

## Usage #####

Post a message by sending a POST request to [ip][port]/message. It should include a parameter (key-value pair) which is "message" : "your message here".

Get all messages by sending a GET request to [ip][port]/message or a specific message by sending a get request to [ip][port]/message/{id}, where id is the id of the message.

Delete a message by sending a DELETE request to [ip][port]/message/{id}, where id is the id of the message.

Update a message by sending a PUT request to [ip][port]/message/{id}, where id is the id of the message. The request should also include a parameter containing the new message.

If a message isn't found the service will respond with a 404. In all successful cases it will respond with a 200.
