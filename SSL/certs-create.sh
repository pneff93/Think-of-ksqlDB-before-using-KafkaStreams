#!/bin/bash

set -o nounset \
    -o errexit \
    -o verbose

for i in broker-1 broker-2 client
do
	echo "------------------------------- $i -------------------------------"

	# Create host Key Store
	keytool -genkey -noprompt -alias $i -dname "CN=$i" -ext "SAN=dns:$i,dns:localhost" -keystore $i-creds/kafka.$i.keystore.jks -keyalg RSA -storepass confluent -keypass confluent

	# Create the certificate signing request (CSR)
	keytool -keystore $i-creds/kafka.$i.keystore.jks -alias $i -certreq -file $i-creds/$i-cert -storepass confluent -keypass confluent

        # Sign the host certificate with the certificate authority (CA)
	openssl x509 -req -CA ./CA/ca-cert -CAkey ./CA/ca-key -in $i-creds/$i-cert -out $i-creds/$i-ca-signed-cert -days 9999 -CAcreateserial -passin pass:confluent

        # Sign and import the CA cert into the keystore
	keytool -noprompt -keystore $i-creds/kafka.$i.keystore.jks -alias CARoot -import -file ./CA/ca-cert -storepass confluent -keypass confluent

        # Sign and import the host certificate into the keystore
	keytool -noprompt -keystore $i-creds/kafka.$i.keystore.jks -alias $i -import -file $i-creds/$i-ca-signed-cert -storepass confluent -keypass confluent

	# Create truststore and import the CA cert
	keytool -noprompt -keystore $i-creds/kafka.$i.truststore.jks -alias CARoot -import -file ./CA/ca-cert -storepass confluent -keypass confluent

	# Save credentials
  	echo "confluent" > ${i}-creds/${i}_credentials
done
