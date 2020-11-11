# Batch Analytics

# Task

Bike sharing is a method to reduce city’s traffic and air pollution. Nowadays, this method is implemented all over the world, due to massive traffic jams and increase of air pollution. There are more than 500,000 bikes around world’s cities to share. One of the cities that used bike sharing program is Washington D.C., USA.

The bike sharing program generates a dataset that contains data of 1000 records of rentals in Washington D.C., from 2011-2013. The data includes date of rental, season, year, month, hour, weekday, weather situation, casual users, and stations. The task is to find out the most popular season and stations. 

The remainder of this report will explain the process of discovering information and data analysis.

# Middleware Configuration

This task uses a standalone setup, using an Ubuntu OS that hosted by the Virtual Machine. The standalone setup is chosen over the pseudo-distributed setup because the standalone setup is the default setup of Hadoop, running as a single Java process. Furthermore, the standalone process is faster to set-up, faster to run, and easier to debug. In the standalone process, there are no daemons running, and the process only runs in a single Java Virtual Machine instance. The file system used is the local file system instead of the HDFS. 

# Data Analytic Design

The dataset that was given is converted to .csv format file instead of .xls format file, because the .csv file is faster and easier to be read in the program. 

As this project will take a long time to complete with a human, the Hadoop MapReduce process will be used to analyze this dataset. MapReduce refers to two different and serial tasks. The Map function organizes the data to a key-value pairs, which in this case, the seasons or the stations will be the key and the number of rents happened to the stations or during the seasons was the value. This function also prepares the data to be used in the reduce function.

In this task, each record was broken down at every comma and two main tokens extracted: seasons and stations. The Java program created have two different sub-function on the map function, called “seasons” and “stations”. If the seasons are counted, the “stations” sub-function must be commented, and if the stations counted, the “seasons” sub-function must be commented.

The result of the process on the map function will be passed to the reduce function, that counted how many times the bikes was rented on a single season or on a single station.

# Results

The result of the MapReduce function gives two files with the results of how many times a bicycle rented during seasons and the stations where it was rented. 

# Discussions of Results

In each season, the average rents are 250 bikes. The most popular season is falling with 329 rents. Compared to other seasons, there’s a difference of more than 100 rents. The next popular season is summer with 235, continued with Winter and Summer, with 218 and 217 each.

The average rent per station on Washington D.C. is 58.76 rents. The Capitol Hill has the most rents with 96, far more than the other stations. Next, there are 9 stations that the total rents are in the range of 60-64. Four more stations were recorded in the range of 47-48, two more on 43. The least popular station is Lincoln, with only 37 rents.

![season](https://github.com/tefohulu/bikesharing/blob/main/Tabel%201.JPG)

![station](https://github.com/tefohulu/bikesharing/blob/main/Tabel%202.JPG)

# Conclusions and Recommendation

During four seasons, the total number of rents is quite uniform, except during the fall season. At the 17 stations, the number of rents was diverse, although more than half of the station’s rent is on the range of 60-64 rents, with the most popular station is Capitol Hill with 96 rents and the least popular is Lincoln Station with 37 rents.

Although this analysis can easily answer the task between 2011-2013, currently, the usage of bikes as public transports increases rapidly. Many companies are also interested in bike usages in another country or another city that might have more data than Washington D.C. 

With this significant increase and demand of bike sharing, the records of bikes rental will increase rapidly, and the usage of HDFS will help to store this huge amount of data records of rentals. HDFS need a hundred to thousands of servers and directly hosts them. With HDFS, the resource can expand with demand. 

Beside HDFS implementation, the usage of multiple MapReduce jobs can improve the speed of the task, because many processes will run instead of one process. 

The output files are another aspect that can be improved. If this project is going to be scaled, each data that will be processed must be put in a specific subfolder or folder, instead of one folder. This will make the analysis proses easier, because the results will be separated into one or more files. 

The implementation of HDFS and MapReduce also can be used on another industry beside the bikes rental, for example, to analyze the trend of purchases on different country for online stores, or the query searching in search engines. The MapReduce and HDFS will make the process easier, faster, and more accurate than human. It also can handle more data, as HDFS storage size can expand with demand.
