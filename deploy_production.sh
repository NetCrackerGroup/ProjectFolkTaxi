#!/bin/bash
rsync -r --quiet --delete-after target/project-folk-taxi-0.0.1-SNAPSHOT.jar $DEPLOY_USER@$DEPLOY_HOST:$DEPLOY_DIRECTORY
ssh $DEPLOY_USER@$DEPLOY_HOST:$DEPLOY_DIRECTORY mkdir "dirname"
echo 'Hello'
echo 'World'