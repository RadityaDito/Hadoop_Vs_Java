# Wordcount Hadoop Vs Java

## Kelompok 9 - SBD 01

- Raditya Ihsan Dhiaulhaq (2106733912)
- Naufal Febriyanto
- Rian Abrar
- Michael Winston

## Pendahuluan

Hadoop merupakan alat yang kuat untuk menganalisa big data. READMe ini akan mengandung cara meng-install Hadoop lalu akan menunjukkan data dari perbandingan nilai yang didapatkan untuk dari hasil memproses word count dengan hadoop dan Java, menganalisa hasil tersebut, dan mencapai suatu kesimpulan akhir yang bisa menjelaskan keseluruhan dari penggunaan Hadoop pada umumnya.

## Instalasi WSL

Pada langkah ini, kita akan menginstal WSL dengan menggunakan Ubuntu 20.04.

1. Untuk menginstal WSL pada Windows, jalankan perintah berikut di Command Prompt atau PowerShell:

   ```bash
   wsl --install -d Ubuntu-20.04
   ```

2. Setelah selesai menginstal, pastikan Ubuntu 20.04 terinstal dengan menjalankan perintah berikut:

   ```bash!
   wsl -l -v
   ```

3. Selanjutnya, jalankan Ubuntu di Windows dengan memilih versi Ubuntu yang terinstal.
   ![](https://hackmd.io/_uploads/Byopx3ed3.png)

## Instalasi Hadoop

Selanjutnya, kita akan menginstal Hadoop.

Referensi: [Kontext](https://kontext.tech/article/978/install-hadoop-332-in-wsl-on-windows)

1. Unduh library Hadoop dengan menjalankan perintah berikut:

   ```bash
   wget https://dlcdn.apache.org/hadoop/common/hadoop-3.3.2/hadoop-3.3.2.tar.gz
   ```

2. Ekstrak library yang telah diunduh dengan menjalankan perintah berikut:

   ```bash
   mkdir ~/hadoop
   tar -xvzf hadoop-3.3.2.tar.gz -C ~/hadoop
   cd ~/hadoop/hadoop-3.3.2/
   ```

3. Edit file `etc/hadoop/hadoop-env.sh` dengan menjalankan perintah berikut:

   ```bash
   sudo nano etc/hadoop/hadoop-env.sh
   ```

   Tambahkan variabel lingkungan JAVA_HOME sebagai berikut:

   ```
   export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
   ```

4. Edit file `etc/hadoop/core-site.xml` dengan menjalankan perintah berikut:

   ```bash
   sudo nano etc/hadoop/core-site.xml
   ```

   Tambahkan konfigurasi berikut:

   ```bash
   <configuration>
        <property>
            <name>fs.defaultFS</name>
            <value>hdfs://localhost:9000</value>
        </property>
   </configuration>
   ```

5. Edit file `etc/hadoop/hdfs-site.xml` dengan menjalankan perintah berikut:

   ```bash
   sudo nano etc/hadoop/hdfs-site.xml
   ```

   Tambahkan konfigurasi berikut:

   ```bash
   <configuration>
        <property>
            <name>dfs.replication</name>
            <value>1</value>
        </property>
        <property>
            <name>dfs.namenode.name.dir</name>
            <value>/home/**Nama Anda**/hadoop/dfs/name332</value>
        </property>
        <property>
            <name>dfs.datanode.data.dir</name>
            <value>/home/**Nama Anda**/hadoop/dfs/data332</value>
        </property>
   </configuration>
   ```

   Selanjutnya, buat dua folder baru dengan menjalankan perintah berikut:

   ```bash
   mkdir -p ~/hadoop/dfs/name332
   mkdir -p ~/hadoop/dfs/data332
   ```

6. Edit file `etc/hadoop/mapred-site.xml` dengan menjalankan perintah berikut:

   ```bash
   sudo nano etc/hadoop/mapred-site.xml
   ```

   Tambahkan konfigurasi berikut:

```bash
   <configuration>
        <property>
            <name>mapreduce.framework.name</name>
            <value>yarn</value>
        </property>
        <property>
            <name>mapreduce.application.classpath</name>
            <value>$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/*:$HADOOP_MAPRED_HOME/share/hadoop/mapreduce/lib/*</value>
        </property>
   </configuration>
```

7. Edit file `etc/hadoop/yarn-site.xml` dengan menjalankan perintah berikut:

   ```bash
   sudo nano etc/hadoop/yarn-site.xml
   ```

   Tambahkan konfigurasi berikut:

   ```xml
   <configuration>
       <property>
           <name>yarn.nodemanager.aux-services</name>
           <value>mapreduce_shuffle</value>
       </property>
       <property>
           <name>yarn.nodemanager.env-whitelist</name>
           <value>JAVA_HOME,HADOOP_COMMON_HOME,HADOOP_HDFS_HOME,HADOOP_CONF_DIR,CLASSPATH_PREPEND_DISTCACHE,HADOOP_YARN_HOME,HADOOP_MAPRED_HOME</value>
       </property>
   </configuration>
   ```

   Selanjutnya, jalankan perintah berikut untuk memformat namenode:

   ```bash
   bin/hdfs namenode -format
   ```

## Cara Menjalankan Hadoop

Gunakan perintah berikut untuk menjalankan Hadoop:

```bash
sbin/start-dfs.sh
sbin/start-yarn.sh
```

Command untuk memasukan file input dan melakukan word count

```bash
hdfs dfs -put 500mb.txt input
bin/yarn jar share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.5.jar wordcount input output
hdfs dfs -cat output/part-r-00000
```

Setelah dijalankan, Anda dapat mengakses portal web YARN di:

http://localhost:8088/cluster

## Device Specification

| Specification | Description                        |
| :------------ | :--------------------------------- |
| CPU           | AMD Ryzen 7 5800H                  |
| GPU           | Nvidia GeForce RTX 3060 Mobile 6GB |
| SSD           | SSD M2 PCI-E Gen 3.0 1x1024 GB     |
| RAM           | 16 GB DDR 4 3200 MHz               |
| OS            | Windows 11 Home 64-bit             |

## Running Time Hadoop dan Java

|File Size| Time with Framework (seconds) | Ratio   |
| :------ |     Java     |     Hadoop     | :------ | 
| 10 Mb   |     1.42     |       21       |  14.78  |
| 100 Mb  |     7.71     |       24       |  3.11   |
| 500 Mb  |     66       |       52       |  0.78   |
| 1 Gb    |     78       |       76       |  0.97   |
| 10 Gb   |     714      |       354      |  0.49   |

## Graph

![](https://cdn.discordapp.com/attachments/1029063611366248501/1121445946643976282/image.png)

## Analysis

Jika dibandingkan, penghitungan kata (wordcount) menggunakan Java dan Hadoop memiliki perbedaan dalam hal waktu eksekusi. Penghitungan kata menggunakan Java cenderung lebih cepat karena data yang dihitung disimpan dan diproses di satu mesin atau server tunggal. Oleh karena itu, overhead yang terkait dengan pengaturan dan komunikasi antar node dalam cluster, seperti yang terjadi pada Hadoop, hanya sedikit. Selain itu, untuk ukuran file yang relatif kecil (10MB dan 100MB), penghitungan kata dengan Java dapat menyelesaikan tugas lebih cepat karena tidak melibatkan kompleksitas yang signifikan.

Di sisi lain, Hadoop dirancang untuk memproses dan menganalisis data dalam skala besar dengan membagi tugas pemrosesan ke beberapa node dalam cluster. Hal ini mengakibatkan terjadinya overhead yang signifikan, terutama ketika digunakan pada platform terdistribusi dan data dengan ukuran besar.



## Conclusion

Pada skala kecil, penghitungan kata biasa tanpa overhead (Java) komunikasi antar node memiliki kinerja yang lebih cepat. Namun, Hadoop menjadi pilihan yang lebih optimal pada skala besar dan pemrosesan yang luas, karena dirancang khusus untuk menangani pemrosesan data dalam skala besar dengan memanfaatkan kekuatan pemrosesan paralel pada beberapa node dalam cluster.
