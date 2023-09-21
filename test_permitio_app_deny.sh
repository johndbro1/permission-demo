#!/bin/sh


#
# is the user john.doe@paclabs.net, with assigned role r_user allowed readonly access to the finance resource?
#
# NO
#

wget -q -O- --post-data \
         '{ "username":"john.doe@paclabs.net", "role":"r_user", "resource":"finance", "param1":"method=GET" }' \
         --header='Content-Type:application/json' \
         --no-check-certificate \
         https://permitio.paclabs.sh:8080/api/v1/main/action


