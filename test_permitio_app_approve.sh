#!/bin/sh


#
# is the user johnbr@paclabs.net, with assigned role r_admin allowed readonly access to the devops resource?
#
# YES
#

HOSTNAME=127.0.0.1

wget -q -O- --post-data \
         '{ "username":"johnbr@paclabs.net", "role":"r_admin", "service":"devops", "param1":"method=GET" }' \
         --header='Content-Type:application/json' \
         --no-check-certificate \
         http://$HOSTNAME:8080/api/v1/main/action


