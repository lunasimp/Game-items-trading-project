DROP DATABASE NewWebsite4

Create DATABASE NewWebsite4;

Use NewWebsite4;
CREATE TABLE UserS(
[User_id] INT IDENTITY(1,1) PRIMARY KEY,
[User_name] NVARCHAR(50),
Username VARCHAR(60),
id_Admin bit,
PASSWORD VARCHAR(20),
Gender NVARCHAR(10),
dob DATE
);


CREATE TABLE Category(
Cat_id int IDENTITY(1,1) PRIMARY KEY,
Cat_name nvarchar(50),
Cat_description nvarchar(100)
);

CREATE TABLE News (
News_id int IDENTITY(1,1) Primary Key,
[User_id] int FOREIGN KEY REFERENCES UserS([User_id]),
Cat_id int FOREIGN KEY REFERENCES Category(Cat_id),
);

CREATE TABLE News_content(
News_id int FOREIGN KEY REFERENCES News(News_id),
News_title nvarchar(100),
News_content nvarchar(MAX),
News_image varchar(50),
Description NVARCHAR(MAX)
);

CREATE TABLE Comment(
Comment_id int IDENTITY(1,1) PRIMARY KEY,
[User_id] int FOREIGN KEY REFERENCES UserS([User_id]),
News_id int FOREIGN KEY REFERENCES News(News_id)
);

CREATE TABLE Comment_content (
Comment_id int FOREIGN KEY REFERENCES Comment(Comment_id),
Comment_content nvarchar(MAX),
)

