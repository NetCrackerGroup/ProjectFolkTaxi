#!/bin/bash
rsync -r --quiet --delete-after target/project-folk-taxi-0.0.1-SNAPSHOT.jar $DEPLOY_USER@$DEPLOY_HOST:$DEPLOY_DIRECTORY
if [ "${TRAVIS_EVENT_TYPE}" = "cron" ]; then
  ssh $DEPLOY_USER@$DEPLOY_HOST ./upgradeScript
  echo 'Cronn'
fi
echo 'World'