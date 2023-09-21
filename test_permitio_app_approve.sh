#!/bin/sh


#
# is the user johnbr@paclabs.net, with assigned role r_admin allowed readonly access to the devops resource?
#
# YES
#

wget -q -O- --post-data \
         '{ "username":"johnbr@paclabs.net", "role":"r_admin", "resource":"devops", "param1":"method=GET" }' \
         --header='Content-Type:application/json' \
         --no-check-certificate \
         https://permitio.paclabs.sh:8080/api/v1/main/action


