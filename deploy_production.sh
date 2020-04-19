#!/bin/bash
rsync -r --quiet --delete-after target/project-folk-taxi-0.0.1-SNAPSHOT.jar $DEPLOY_USER@$DEPLOY_HOST:$DEPLOY_DIRECTORY