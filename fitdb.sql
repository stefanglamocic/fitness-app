-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: fitdb
-- ------------------------------------------------------
-- Server version	8.0.36-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attribute`
--

DROP TABLE IF EXISTS `attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attribute` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attribute`
--

LOCK TABLES `attribute` WRITE;
/*!40000 ALTER TABLE `attribute` DISABLE KEYS */;
INSERT INTO `attribute` VALUES ('Bučice'),('Kablovi'),('Mašine'),('Medicinske lopte'),('Novi atribut'),('Ostalo'),('Šipka'),('Tjelesna težina'),('Trake');
/*!40000 ALTER TABLE `attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Funkcionalni trening'),(2,'Kardio'),(3,'Trening snage'),(4,'Joga'),(5,'Zumba'),(22,'Full body'),(23,'Full body'),(26,'Split'),(27,'Sportski trening'),(28,'Split'),(29,'Jedna mišićna grupa'),(30,'Jedna mišićna grupa'),(31,'Nova kategorija');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_has_attribute`
--

DROP TABLE IF EXISTS `category_has_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_has_attribute` (
  `category_id` int NOT NULL,
  `attribute_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`,`attribute_name`),
  KEY `fk_category_has_attribute_attribute1_idx` (`attribute_name`),
  KEY `fk_category_has_attribute_category1_idx` (`category_id`),
  CONSTRAINT `fk_category_has_attribute_attribute1` FOREIGN KEY (`attribute_name`) REFERENCES `attribute` (`name`),
  CONSTRAINT `fk_category_has_attribute_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_has_attribute`
--

LOCK TABLES `category_has_attribute` WRITE;
/*!40000 ALTER TABLE `category_has_attribute` DISABLE KEYS */;
INSERT INTO `category_has_attribute` VALUES (1,'Bučice'),(1,'Šipka'),(1,'Tjelesna težina'),(2,'Mašine'),(2,'Tjelesna težina'),(3,'Bučice'),(3,'Kablovi'),(3,'Šipka'),(3,'Tjelesna težina'),(4,'Tjelesna težina'),(5,'Ostalo'),(5,'Tjelesna težina'),(22,'Medicinske lopte'),(22,'Šipka'),(22,'Tjelesna težina'),(23,'Bučice'),(23,'Šipka'),(26,'Bučice'),(26,'Kablovi'),(26,'Šipka'),(26,'Tjelesna težina'),(27,'Bučice'),(27,'Medicinske lopte'),(27,'Šipka'),(27,'Tjelesna težina'),(28,'Bučice'),(28,'Tjelesna težina'),(29,'Tjelesna težina'),(30,'Bučice'),(30,'Mašine'),(30,'Ostalo'),(31,'Novi atribut'),(31,'Šipka'),(31,'Tjelesna težina');
/*!40000 ALTER TABLE `category_has_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `fitness_program_id` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `published` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_fitness_program_id` int DEFAULT NULL,
  `comment_username` varchar(20) DEFAULT NULL,
  `comment_published` datetime DEFAULT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`fitness_program_id`,`username`,`published`),
  KEY `fk_comment_user1_idx` (`username`),
  KEY `fk_comment_comment1_idx` (`comment_fitness_program_id`,`comment_username`,`comment_published`),
  CONSTRAINT `fk_comment_comment1` FOREIGN KEY (`comment_fitness_program_id`, `comment_username`, `comment_published`) REFERENCES `comment` (`fitness_program_id`, `username`, `published`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_fitness_program` FOREIGN KEY (`fitness_program_id`) REFERENCES `fitness_program` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'admin','2024-04-18 03:47:11',1,'test','2024-04-05 22:02:40','veoma bitno!!!'),(1,'marko','2024-04-18 03:51:53',1,'proba','2024-04-03 19:11:22','drvo struktura test'),(1,'proba','2024-04-02 23:56:17',NULL,NULL,NULL,'komentar na program'),(1,'proba','2024-04-03 19:11:22',NULL,NULL,NULL,'drugi komentar'),(1,'test','2024-04-05 22:02:40',1,'proba','2024-04-03 19:11:22','odgovor hehe'),(5,'admin','2024-04-23 00:27:49',NULL,NULL,NULL,'postman'),(5,'admin','2024-04-23 00:38:42',NULL,NULL,NULL,'nova tura, nova avantura'),(5,'marko','2024-04-23 00:56:13',5,'admin','2024-04-23 00:27:49','odgovaram'),(10,'admin','2024-04-23 02:09:35',NULL,NULL,NULL,'isprobavam komentare'),(10,'admin','2024-04-23 02:11:04',NULL,NULL,NULL,'jos jednom'),(10,'admin','2024-04-23 02:48:41',10,'admin','2024-04-23 02:09:35','odgovaram samom sebi'),(31,'admin','2024-04-23 11:57:57',NULL,NULL,NULL,'komentarisanje'),(31,'admin','2024-04-23 11:58:06',31,'admin','2024-04-23 11:57:57','odgovor');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fitness_program`
--

DROP TABLE IF EXISTS `fitness_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fitness_program` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `difficulty_level` varchar(45) DEFAULT NULL,
  `duration` float DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `category_id` int NOT NULL,
  `instructor_id` int NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `hidden` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_fitness_program_category1_idx` (`category_id`),
  KEY `fk_fitness_program_instructor1_idx` (`instructor_id`),
  KEY `fk_fitness_program_user1_idx` (`created_by`),
  CONSTRAINT `fk_fitness_program_category1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_fitness_program_instructor1` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`),
  CONSTRAINT `fk_fitness_program_user1` FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fitness_program`
--

LOCK TABLES `fitness_program` WRITE;
/*!40000 ALTER TABLE `fitness_program` DISABLE KEYS */;
INSERT INTO `fitness_program` VALUES (1,'Lorem ipsum',' Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum semper efficitur elit tincidunt efficitur. Suspendisse pharetra volutpat felis et eleifend. Vestibulum aliquet hendrerit condimentum. Fusce ex orci, iaculis eget sagittis non, ullamcorper quis neque. Pellentesque laoreet volutpat fringilla. Fusce quis risus nec turpis interdum mattis vel ut felis. Nunc ac neque sit amet erat vestibulum aliquet. Cras ornare nec massa ac tempor. Quisque tortor diam, convallis quis imperdiet eget, aliquam id magna. Nulla tempus eros varius, iaculis elit ac, iaculis velit. Ut molestie ipsum eget diam pellentesque euismod a in augue. Nam auctor semper eros nec facilisis.\n\nCras bibendum gravida gravida. Cras posuere rhoncus eleifend. In ante quam, tristique a mattis a, varius et nunc. Integer sit amet quam at nulla eleifend aliquam laoreet ac mi. Aliquam lacinia in diam in maximus. Ut venenatis massa a aliquam egestas. Fusce non volutpat arcu. Mauris aliquet posuere enim, id suscipit lectus ullamcorper eu. Phasellus finibus purus velit, sit amet sollicitudin augue malesuada sit amet. Maecenas eu nisi aliquet, pulvinar arcu id, vestibulum neque. Aenean iaculis hendrerit aliquet. Maecenas eget justo nec tortor vestibulum gravida.\n\nAenean sit amet luctus libero. Ut in nisi aliquam, sagittis nisi sit amet, ultricies nunc. Sed nisl dui, lobortis in egestas vel, imperdiet vitae ex. Cras auctor tempor ipsum a finibus. Proin tristique pretium efficitur. Aliquam id velit euismod dolor varius pretium ac eget lacus. Maecenas euismod suscipit nunc, sit amet ultricies elit dictum ac. ',200,'Advanced',8,'Gym',1,1,'admin',0),(5,'Basketball Workouts','The following workout routine is a 2 days per week full body workout routine for basketball players currently in-season. The workouts were created to focus on injury prevention and lean muscle and strength maintenance. They emphasize core strength as well as muscle groups surrounding common areas of injury concern for basketball players: knees, ankles, lower back, and groin. The workout as written shouldn’t take you longer than 30 minutes to perform. Keep your rest periods to a minimum. They should only last between 30-60 seconds in between each set. The workouts should be performed the day after a game. For most athletes who play 2 games per week, you’re most likely to play on a Monday and Thursday and/or Tuesday and Friday. By performing the workouts on the day after a game, you’ll allow adequate time to recover prior to your next game.This will also likely coincide with your most intense practices of the week as well. Making it a tough training day, but you got this.',79.99,'Beginner',20,'Gym',22,2,'admin',0),(6,'Fight to be Fit','This full body workout incorporates some fighting style cardio with a high volume weight training routine to help you fight for your fitness. This workout is designed as a 6X8X10 series that focuses on six body parts, for eight sets of ten reps.',156,'Beginner',6,'Gym',23,2,'admin',0),(7,'10 Week Women’s Fat Loss Workout','This 10 week women\'s fat loss training program is perfect for any healthy woman who is looking to transform her body through a good weight lifting program. The goal is to increase the amount of calories you burn by performing exercises that require multiple muscle groups to help complete the exercise. These are known as compound lifts and will help you burn way more calories than isolation exercises will in the long run. By the end of the 10 weeks you should notice tighter, slimmer, and stronger muscles and a better body composition than when you began.',150,'Intermediate',10,'Gym',26,4,'admin',0),(8,'Basketball Performance Workout: Building A Better Baller','Train like a NBA pro with this baller workout that not only covers technical skills, but also helps to build solid muscle and increase strength! ',83,'Intermediate\n',6,'Online',27,3,'admin',1),(9,'Summer Shape Up at Home: Women\'s 6 Week Fat-Burning Workout','Are you stuck working out at home, and still want to look and feel your best for summer? This 6-week fat-burning workout will have you beach-ready in no time!',56.6,'Beginner',6,'Online',28,4,'admin',0),(10,'Bulldozer Training 3 Day Workout Split','Limited on time? This 3 day training split combines rest-pause sets with progressive resistance to help you build solid muscle in a short amount of time. Get ready to intensify your lifts!',119.99,'Intermediate',8,'Gym',26,1,'admin',0),(11,'3 Day Full Body Planet Fitness Workout','In this edition of a series of Planet Fitness appropriate workouts, we provide a 3 day full body workout one could do with the equipment at Planet Fitness.',100,'Beginner',8,'Gym',23,2,'admin',0),(12,'15-Minute Core Conditioning Workout You Can Do Anywhere','Train your abs effectively and efficiently! This versatile workout can be done with just your bodyweight and should be incorporated into your training plan 2-3 times per week.',19.99,'Beginner',8,'Online',29,2,'admin',0),(13,'6 Day Push/Pull/Legs (PPL) Powerbuilding Workout Split & Meal Plan','This 6 day push/pull/legs workout routine split is a high volume, rest-pause system designed for intermediate lifters looking to gain muscle and strength. Each workout starts out with a compound lift using a 15 rep goal over 5 sets. If you exceed the rep goal by 0-3 reps then add 2.5-5lbs to the working weight the next time you perform the exercise. If you exceed the rep goal by 4+ reps then add 5-10lbs to the working weight. Starting out with a heavy compound lift will is a great way to increase central nervous system (CNS) activity, improve strength, and gain confidence moving heavy loads. When picking a start weight for this lift I would recommend using your 5-6 rep max (RM). After the 5 sets of the heavy compound lift you’re going to perform a back-off set in which you decrease the working weight by 20% (round to the nearly 2.5 or 5lbs) and try to perform as many quality reps as possibly (AMQRAP). This set is less taxing on the CNS and allows for some extra volume on the main compound lift of the day, which is helpful for muscle growth and reinforcing proper form. Proportionally increase the working weight of AMQRAP set by 2.5-10lbs every time you increase the weight during the 15 rep goal sets. After the main compound movement of the day you’re going to perform rest-pause/bulldozer sets for the remainder of the workout. You will be resting 15 to 60 seconds between each set of an exercise and 2 to 3 minutes between each exercise. The accessory work will begin with compound exercises using moderate loads and will conclude will isolation, pump-inducing exercises using relatively low loads. It’s important that you keep track of your timing in between sets – not only to optimize muscle growth but also to prevent you from being in the gym forever. I program my accessory work with high volumes and rest-pause because I believe it’s an effective way to trigger muscle growth in a short period of time while allowing me to leave the gym with a nice pump. The exercise selection I lay out in the routine below is by no means set in stone, but I encourage you to be wise if and how you decide to make substitutions. For example, substituting conventional deadlift for sumo deadlift is completely reasonable but substituting chin-ups for another curl exercise is not advisable.  Once you pick your exercises stick with them until you stop progressing; there’s no reason to “confuse” your muscles if you’re adding reps and/or weight to the bar every time you perform an exercise. If you’re used to low volume routines this routine will initially make you quite sore – you can either power through the soreness or gradually increase the volume over time. For example, instead of a 50 rep goal over 5 sets with shrugs you could aim for a 30 rep goal over 3 sets the 1st week, 40 reps over 4 sets the 2nd, and 50 reps over 5 sets the 3rd week.',165,'Intermediate',12,'Gym',26,3,'admin',0),(14,'4 Week Fat Loss Intermediate Plyometric Workout','Tired of trotting along on the treadmill to get your cardio in? Make the switch to plyometrics for explosive fat loss results with this 4 week intermediate plan! It’s divided into 3 days and can be used as a form of cardio to your normal workout routine. Perform each one of these workouts on Monday, Wednesday, and Friday. Volume will vary slightly, but most of the time we will be going for as many reps as you can in a certain amount of time. This workout should be completed within 20 minutes or so and can be done anywhere.',64.99,'Intermediate',4,'Online',2,2,'admin',0),(15,'4 Ridiculously Killer HIIT Treadmill Routines','Don\'t fall into the winter weight gain trap this year. While it might be too cold to do cardio outside, these 4 treadmill workouts will keep the weight off! Unless you are lucky enough to live in a tropical area, here in the Midwest we spend 5-6 months every year doing the majority of our workouts in doors. One of the great joys of summertime is getting to run and exercise outside. The possibilities are endless - actual hill sprints, bleacher and stair runs, track sprint workouts, or just a mind clearing jog through the neighborhood. For me it’s hard to get bored with running during the summer, and my body benefits from the variety of the different terrain and types of workouts available to me outdoors. Now that autumn is here and winter fast approaching, the possibility of regular outdoor runs becomes less and less likely, and long stretches on the treadmill becomes the likely reality. It would be easy to pack it in until spring time and gain the dreaded “winter 10” on the scale, but that’s not my style. This time of year forces me to be a little creative with my conditioning plan so I don’t end up hating the treadmill or avoiding it all together. Luckily for you I have 4 new intense treadmill routines that are sure to break the soon-to-be winter blues and monotony of running in place.',58.8,'Intermediate',12,'Gym',2,2,'admin',0),(16,'Back Blast! 8 Week Program to Build Your Back','Blast your back with this back workout and experience some serious back gains! This comprehensive workout hits every muscle in the back for optimal growth! You know the type of physique that really turns heads and makes a statement? One that is impressive from all angles.\n\nWide shoulders, big legs, and especially a broad and thick back are necessities to have for a great physique. When someone turns around and you can see muscles from left to right and top to bottom, you are assured that he knows what he’s doing in a weight room.\n\nMastering your physique takes years but you need to start if you want to make it happen. This program will help you make progress quickly and seeing those results will generate enough momentum for you that you’ll want to keep going.\n\nIf you focus on working and don’t waste time, it shouldn’t take you longer than an hour.\n\nAs you learn more about these exercises you’re going to notice that it isn’t only about lifting heavy weights and banging out reps.\n\nTechnique and execution are vital if you want to make those muscle building aspirations your new reality.',90,'Intermediate',8,'Gym',30,1,'admin',0),(17,'8-Week Knee-Friendly Quad Workout','This program can help you build bigger legs without taking a big toll on the knees. Incorporate this workout into your existing training plan once a week for massive gains. Let’s be honest for a minute. Leg training isn’t necessarily the most popular conversation to have when it comes to working out. However, it is important and should be had because a complete physique includes having strong and muscular legs.\n\nOne reason for some people’s reluctance to take on leg day is they have knee issues, and the dread of feeling knee pain for days after the session ends makes some trainees feel the effort isn’t worth it.\n\nHere’s the good news. As long as a doctor supports your overall training efforts, then there are ways to make the most out of lower body training while minimizing the toll that it takes on the knees.\n\nIt’s impossible to guarantee that knee pain will go away completely, but implementing these tips, along with the workout that is included, can help you make the most out of your quadriceps training, which is usually the hardest part of the leg workout.',87.7,'Beginner',8,'Gym',30,1,'admin',0),(18,'8 Week Mass Building Workout for Beginners','This 4 day workout will help you stretch sleeves, build mass, and bump up that number on the scale. You\'ll also learn about proper nutrition for your mass building goals.\nMany people who find their way to Muscle & Strength are looking to build just that – muscle and strength. Whether it’s a teenager just starting in the gym or a college student trying to impress others, size is the prize, and they want to do whatever it takes to get it.\n\nGood news: we have a plan that will help you stretch sleeves, build mass, and bump up that number on the scale. If you can commit eight weeks to this as if your future depends on it, then you can kickstart your journey to getting swole.',125,'Beginner',8,'Gym',26,1,'admin',0),(19,'Hollywood Muscle: Sylvester Stallone Inspired Workout Routine','Work out like Rambo and Rocky with this workout routine inspired by Sylvester Stallone in this month\'s edition of Hollywood Muscle. Check it out!',150,'Advanced',8,'Gym',26,3,'admin',0),(20,'Building The Beginner: Foundation For Muscle & Strength','Building The Beginner is a 6 week program that lays the foundation for heavier lifting. A detailed training plan with instructional videos makes it easy to follow!',177,'Beginner',6,'Gym',26,4,'admin',0),(21,'4 Day Power Muscle Burn Workout Split','This building workout is designed to maximize progression and muscle gains by hitting each muscle group with power sets, muscle sets and burn sets.',123,'Intermediate',12,'Gym',26,2,'marko',0),(23,'5 Day Muscle & Strength Building Workout Split','test',180,'Intermediate',10,'Gym',26,1,'marko',0),(24,'3 Day Full Body Toning Workout for Women','This full body toning workout hits all the major muscle groups in just 30 minutes, and is perfect for people who want to strengthen their muscles!',79,'Intermediate',4,'Online',22,4,'marko',0),(25,'Adonis Creed Inspired Workout: Shred Fat like a Contender','Train like Rocky Balboa\'s protege, Adonis Creed, with this workout program that mixes weight training & cardio for a balanced muscle building approach.',66,'Intermediate',8,'Online',28,1,'marko',0),(26,'Summer Shape Up: Women\'s 6 Week Fat-Burning Workout','Hey ladies, get ready and stay ready for all your summer events with this 6-week fat loss workout! You\'ll not only look great, but you\'ll feel great, too.',45,'Beginner',3,'Gym',4,4,'marko',0),(27,'5 Week Full Body Kettlebell Workout','opis',62,'Beginner',6,'Gym',5,2,'marko',0),(28,'4 Week “V Cuts Abs” Workout Routine','aaasd',12,'Intermediate',2,'Online',23,3,'marko',0),(29,'Hollywood Muscle: Zac Efron Inspired Workout Routine','adafaf',180,'advanced',10,'Online',3,2,'marko',0),(30,'Tactical Tabata: 30 Day High-Intensity Workout','zzxzva',112,'Intermediate',6,'Online',4,1,'marko',0),(31,'novi program','neki opis',22,'Intermediate',3,'Gym',5,2,'admin',0);
/*!40000 ALTER TABLE `fitness_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fitness_program_has_user`
--

DROP TABLE IF EXISTS `fitness_program_has_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fitness_program_has_user` (
  `fitness_program_id` int NOT NULL,
  `username` varchar(20) NOT NULL,
  `completed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`fitness_program_id`,`username`),
  KEY `fk_fitness_program_has_user_user1_idx` (`username`),
  KEY `fk_fitness_program_has_user_fitness_program1_idx` (`fitness_program_id`),
  CONSTRAINT `fk_fitness_program_has_user_fitness_program1` FOREIGN KEY (`fitness_program_id`) REFERENCES `fitness_program` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_fitness_program_has_user_user1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fitness_program_has_user`
--

LOCK TABLES `fitness_program_has_user` WRITE;
/*!40000 ALTER TABLE `fitness_program_has_user` DISABLE KEYS */;
INSERT INTO `fitness_program_has_user` VALUES (1,'admin',0),(5,'admin',0),(6,'admin',0),(8,'admin',0),(8,'marko',0),(13,'marko',0);
/*!40000 ALTER TABLE `fitness_program_has_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `path` varchar(255) NOT NULL,
  PRIMARY KEY (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('https://cdn.muscleandstrength.com/sites/default/files/20_minute_hiit_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/3_day_dumbbell_only_workout_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/3_day_toning_workout_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/4_day_maximum_mass_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/5_week_kettlebell_workout_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/6_day_ppl_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/cropped-image-of-dark-skinned-male-athlete-touching-knee-feeling-sore-muscles-after-injury-in-gym.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/4-killer-treadmill-routines-feature.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/basketball_performance_workout_feature.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/building-the-beginner-featured.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/machine-only-thumb.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/10-week-womens-fat-loss-workout.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/1000x500_1.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/3-day-planet-fitness-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/4-day-planet-fitness-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/5-day-muscle-and-strength-workout.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/back-blast-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/basketball-in-season-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/bodyblaster1000.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/creed-workout-program-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/decline-bodyweight-pushup.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/fight-to-be-fit-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/intermediate-plyometric-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/kettlebell-circuit-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/man-doing-push-up-workout-in-bedroom-1000.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/sylvester-stallone-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/v-cut-abs-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/womens-upper-lower-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/zac-efron-workout-wide.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/fit-female-doing-glute-workouts-outside.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/hift_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/hit3_home_workout_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2021/03/healthy-brunch-flatlay.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2021/06/woman-bicep-curl-gym.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/01/blonde-woman-using-hack-squat-machine.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/01/fit-girl-stretching-in-the-gym.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/06/man_and_woman_doing_side_planks_outside.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2023/10/muscular_man_cooking_food.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/2023/10/muscular_man_with_dumbbells.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/4-killer-treadmill-routines.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/bent-over-rear-fly.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/injury-prone.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/plyometrics_for_improved_jumping_ability.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/muscular_man_doing_tricep_push_down.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/phul_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/power_muscle_burn_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/shirtless_male_doing_ab_workout_at_home.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/summer_shape_up_gym_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/summer_shape_up_home_-_1200x630.jpg'),('https://cdn.muscleandstrength.com/sites/default/files/tacticalphysique1200x630-min.jpeg'),('https://cdn.muscleandstrength.com/sites/default/files/tactical_tabata_-_1200x630.jpg');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_has_fitness_program`
--

DROP TABLE IF EXISTS `image_has_fitness_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_has_fitness_program` (
  `image_path` varchar(255) NOT NULL,
  `fitness_program_id` int NOT NULL,
  PRIMARY KEY (`image_path`,`fitness_program_id`),
  KEY `fk_image_has_fitness_program_fitness_program1_idx` (`fitness_program_id`),
  KEY `fk_image_has_fitness_program_image1_idx` (`image_path`),
  CONSTRAINT `fk_image_has_fitness_program_fitness_program1` FOREIGN KEY (`fitness_program_id`) REFERENCES `fitness_program` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_image_has_fitness_program_image1` FOREIGN KEY (`image_path`) REFERENCES `image` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_has_fitness_program`
--

LOCK TABLES `image_has_fitness_program` WRITE;
/*!40000 ALTER TABLE `image_has_fitness_program` DISABLE KEYS */;
INSERT INTO `image_has_fitness_program` VALUES ('https://cdn.muscleandstrength.com/sites/default/files/20_minute_hiit_-_1200x630.jpg',1),('https://cdn.muscleandstrength.com/sites/default/files/4_day_maximum_mass_-_1200x630.jpg',1),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/4-day-planet-fitness-wide.jpg',1),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/basketball-in-season-workout-wide.jpg',5),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/injury-prone.jpg',5),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/fight-to-be-fit-workout-wide.jpg',6),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/10-week-womens-fat-loss-workout.jpg',7),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/womens-upper-lower-workout-wide.jpg',7),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/basketball_performance_workout_feature.jpg',8),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/plyometrics_for_improved_jumping_ability.jpg',8),('https://cdn.muscleandstrength.com/sites/default/files/images/2021/06/woman-bicep-curl-gym.jpg',9),('https://cdn.muscleandstrength.com/sites/default/files/summer_shape_up_home_-_1200x630.jpg',9),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/1000x500_1.jpg',10),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/3-day-planet-fitness-workout-wide.jpg',11),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/06/man_and_woman_doing_side_planks_outside.jpg',12),('https://cdn.muscleandstrength.com/sites/default/files/shirtless_male_doing_ab_workout_at_home.jpg',12),('https://cdn.muscleandstrength.com/sites/default/files/6_day_ppl_-_1200x630.jpg',13),('https://cdn.muscleandstrength.com/sites/default/files/images/2021/03/healthy-brunch-flatlay.jpg',13),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/intermediate-plyometric-workout-wide.jpg',14),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/4-killer-treadmill-routines-feature.jpg',15),('https://cdn.muscleandstrength.com/sites/default/files/images/articles/4-killer-treadmill-routines.jpg',15),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/back-blast-workout-wide.jpg',16),('https://cdn.muscleandstrength.com/sites/default/files/cropped-image-of-dark-skinned-male-athlete-touching-knee-feeling-sore-muscles-after-injury-in-gym.jpg',17),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/01/blonde-woman-using-hack-squat-machine.jpg',17),('https://cdn.muscleandstrength.com/sites/default/files/images/2022/01/fit-girl-stretching-in-the-gym.jpg',17),('https://cdn.muscleandstrength.com/sites/default/files/images/2023/10/muscular_man_cooking_food.jpg',18),('https://cdn.muscleandstrength.com/sites/default/files/images/2023/10/muscular_man_with_dumbbells.jpg',18),('https://cdn.muscleandstrength.com/sites/default/files/muscular_man_doing_tricep_push_down.jpg',18),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/sylvester-stallone-workout-wide.jpg',19),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-image/workout/building-the-beginner-featured.jpg',20),('https://cdn.muscleandstrength.com/sites/default/files/power_muscle_burn_-_1200x630.jpg',21),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/5-day-muscle-and-strength-workout.jpg',23),('https://cdn.muscleandstrength.com/sites/default/files/3_day_toning_workout_-_1200x630.jpg',24),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/creed-workout-program-wide.jpg',25),('https://cdn.muscleandstrength.com/sites/default/files/summer_shape_up_gym_-_1200x630.jpg',26),('https://cdn.muscleandstrength.com/sites/default/files/5_week_kettlebell_workout_-_1200x630.jpg',27),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/v-cut-abs-wide.jpg',28),('https://cdn.muscleandstrength.com/sites/default/files/field/feature-wide-image/workout/zac-efron-workout-wide.jpg',29),('https://cdn.muscleandstrength.com/sites/default/files/tactical_tabata_-_1200x630.jpg',30),('https://cdn.muscleandstrength.com/sites/default/files/6_day_ppl_-_1200x630.jpg',31),('https://cdn.muscleandstrength.com/sites/default/files/cropped-image-of-dark-skinned-male-athlete-touching-knee-feeling-sore-muscles-after-injury-in-gym.jpg',31);
/*!40000 ALTER TABLE `image_has_fitness_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `information` varchar(200) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'Pavle Radic','Dipl. prof. fizičkog vaspitanja i sporta Personalni i grupni trener','pavle-radic@hotmail.com'),(2,'Milan Zaric','Licni trener u centru Beograda, zvanicno najbolji fitness trener u Srbiji.','info@licnitrener.com'),(3,'Srecko Stanisic','Besplatan probni trening! MOGUCNOST ONLINE TRENINGA.','065334309'),(4,'Sanja i Sanjin Imanić','Jednostavno rečeno, nudimo vam usluge uživo poluindividualnog treninga i online personalnog treninga i s nama možete postići vrhunske rezultate ukoliko ste disciplinovani.','sanjin.imanic@gmail.com');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `sender` varchar(20) NOT NULL,
  `receiver` varchar(20) NOT NULL,
  `time_sent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `opened` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sender`,`receiver`,`time_sent`),
  KEY `fk_message_user2_idx` (`receiver`),
  CONSTRAINT `fk_message_user1` FOREIGN KEY (`sender`) REFERENCES `user` (`username`) ON DELETE CASCADE,
  CONSTRAINT `fk_message_user2` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('admin','ana','2024-04-22 16:45:46','lorem ipsum test1111',1),('admin','marko','2024-04-23 11:58:54','poruka savjetniku',1),('ana','admin','2024-04-22 03:10:09','najnovija poruka',1),('ana','marko','2024-02-23 23:20:26','Prva poruka 1122',1),('ana','marko','2024-02-23 23:20:53','druga anina poruka',0),('ana','marko','2024-04-22 19:14:05','nova probna poruka od ane',1),('marko','admin','2024-03-21 02:13:05','test poruka adminu',0),('nikolina','ana','2024-02-24 00:03:32','test',0),('nikolina','marko','2024-02-23 23:20:26','Pozdrav drugar!',0),('nikolina','marko','2024-02-24 12:20:29','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `user_type` varchar(1) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `city` varchar(20) DEFAULT NULL,
  `mail` varchar(45) NOT NULL,
  `activated` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','A','Gojko','Gojković','Gornji Milanovac','ggojko@mail.com',1),('ana','ana','F','Ana','Anković','Laktaši','aa@mail.com',1),('marko','marko','S','Marko','Marković','Gradiška','markomarkovic@firemail.cc',1),('nikolina','nikolina','F','Nikolina','Ninić','Bileća','nikolinan@firemail.cc',1),('proba','proba','F','Probni','Korisnik','Šipovo','proba@mail.com',1),('test','test','F','Testni','Korisnik','London','test@mail.com',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-26  1:55:26
