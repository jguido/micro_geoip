FROM java:8

COPY target/scala-2.11/micro-geoip.jar /micro-geoip.jar

COPY docker-run.sh /docker-run.sh

COPY src/test/resources/GeoLiteCity.dat /GeoLiteCity.dat

WORKDIR /

EXPOSE 80

RUN mv docker-run.sh run.sh && \
    chmod 777 run.sh

RUN chmod 777 GeoLiteCity.dat

CMD sh run.sh
