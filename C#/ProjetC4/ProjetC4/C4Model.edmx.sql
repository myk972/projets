
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, and Azure
-- --------------------------------------------------
-- Date Created: 02/22/2013 09:38:17
-- Generated from EDMX file: C:\Users\Dev\documents\visual studio 2012\Projects\ProjetC4\ProjetC4\C4Model.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [C4DataBase];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[FK_PackageRoute]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListPackage] DROP CONSTRAINT [FK_PackageRoute];
GO
IF OBJECT_ID(N'[dbo].[FK_VehicleGdz]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListVehicle] DROP CONSTRAINT [FK_VehicleGdz];
GO
IF OBJECT_ID(N'[dbo].[FK_VehicleSlot]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListVehicle] DROP CONSTRAINT [FK_VehicleSlot];
GO
IF OBJECT_ID(N'[dbo].[FK_RouteStage]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListStage] DROP CONSTRAINT [FK_RouteStage];
GO
IF OBJECT_ID(N'[dbo].[FK_SlotCenter]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListSlot] DROP CONSTRAINT [FK_SlotCenter];
GO
IF OBJECT_ID(N'[dbo].[FK_VehicleCenter]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListVehicle] DROP CONSTRAINT [FK_VehicleCenter];
GO
IF OBJECT_ID(N'[dbo].[FK_SlotPackage]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ListPackage] DROP CONSTRAINT [FK_SlotPackage];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[ListEmployee]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListEmployee];
GO
IF OBJECT_ID(N'[dbo].[ListCenter]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListCenter];
GO
IF OBJECT_ID(N'[dbo].[ListGdz]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListGdz];
GO
IF OBJECT_ID(N'[dbo].[ListPackage]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListPackage];
GO
IF OBJECT_ID(N'[dbo].[ListRoute]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListRoute];
GO
IF OBJECT_ID(N'[dbo].[ListStage]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListStage];
GO
IF OBJECT_ID(N'[dbo].[ListVehicle]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListVehicle];
GO
IF OBJECT_ID(N'[dbo].[ListSlot]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ListSlot];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'ListEmployee'
CREATE TABLE [dbo].[ListEmployee] (
    [ID] int IDENTITY(1,1) NOT NULL,
    [FirstName] nvarchar(max)  NOT NULL,
    [LastName] nvarchar(max)  NOT NULL,
    [Username] nvarchar(max)  NOT NULL,
    [Password] nvarchar(max)  NOT NULL,
    [AccessLevel] nvarchar(max)  NOT NULL
);
GO

-- Creating table 'ListCenter'
CREATE TABLE [dbo].[ListCenter] (
    [Code] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Location] geography  NOT NULL,
    [Address] nvarchar(max)  NOT NULL,
    [Type] nvarchar(max)  NOT NULL,
    [Group] nvarchar(max)  NOT NULL
);
GO

-- Creating table 'ListGdz'
CREATE TABLE [dbo].[ListGdz] (
    [ID] int IDENTITY(1,1) NOT NULL,
    [Coordinates] geography  NOT NULL
);
GO

-- Creating table 'ListPackage'
CREATE TABLE [dbo].[ListPackage] (
    [Code] int IDENTITY(1,1) NOT NULL,
    [Name] nvarchar(max)  NOT NULL,
    [Sender] nvarchar(max)  NOT NULL,
    [SenderAddress] nvarchar(max)  NOT NULL,
    [Receiver] nvarchar(max)  NOT NULL,
    [DestinationAddress] nvarchar(max)  NOT NULL,
    [Comment] nvarchar(max)  NOT NULL,
    [Route_ID] int  NOT NULL,
    [Slot_Code] int  NULL
);
GO

-- Creating table 'ListRoute'
CREATE TABLE [dbo].[ListRoute] (
    [ID] int IDENTITY(1,1) NOT NULL
);
GO

-- Creating table 'ListStage'
CREATE TABLE [dbo].[ListStage] (
    [ID] int IDENTITY(1,1) NOT NULL,
    [Type] nvarchar(max)  NOT NULL,
    [DatePackageAtStage] datetime  NULL,
    [Route_ID] int  NOT NULL
);
GO

-- Creating table 'ListVehicle'
CREATE TABLE [dbo].[ListVehicle] (
    [ID] int IDENTITY(1,1) NOT NULL,
    [Number] nvarchar(max)  NOT NULL,
    [Gdz_ID] int  NOT NULL,
    [Slot_Code] int  NOT NULL,
    [Center_Code] int  NOT NULL
);
GO

-- Creating table 'ListSlot'
CREATE TABLE [dbo].[ListSlot] (
    [Code] int IDENTITY(1,1) NOT NULL,
    [MaxCapacity] int  NOT NULL,
    [FreeCapacity] int  NOT NULL,
    [Center_Code] int  NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [ID] in table 'ListEmployee'
ALTER TABLE [dbo].[ListEmployee]
ADD CONSTRAINT [PK_ListEmployee]
    PRIMARY KEY CLUSTERED ([ID] ASC);
GO

-- Creating primary key on [Code] in table 'ListCenter'
ALTER TABLE [dbo].[ListCenter]
ADD CONSTRAINT [PK_ListCenter]
    PRIMARY KEY CLUSTERED ([Code] ASC);
GO

-- Creating primary key on [ID] in table 'ListGdz'
ALTER TABLE [dbo].[ListGdz]
ADD CONSTRAINT [PK_ListGdz]
    PRIMARY KEY CLUSTERED ([ID] ASC);
GO

-- Creating primary key on [Code] in table 'ListPackage'
ALTER TABLE [dbo].[ListPackage]
ADD CONSTRAINT [PK_ListPackage]
    PRIMARY KEY CLUSTERED ([Code] ASC);
GO

-- Creating primary key on [ID] in table 'ListRoute'
ALTER TABLE [dbo].[ListRoute]
ADD CONSTRAINT [PK_ListRoute]
    PRIMARY KEY CLUSTERED ([ID] ASC);
GO

-- Creating primary key on [ID] in table 'ListStage'
ALTER TABLE [dbo].[ListStage]
ADD CONSTRAINT [PK_ListStage]
    PRIMARY KEY CLUSTERED ([ID] ASC);
GO

-- Creating primary key on [ID] in table 'ListVehicle'
ALTER TABLE [dbo].[ListVehicle]
ADD CONSTRAINT [PK_ListVehicle]
    PRIMARY KEY CLUSTERED ([ID] ASC);
GO

-- Creating primary key on [Code] in table 'ListSlot'
ALTER TABLE [dbo].[ListSlot]
ADD CONSTRAINT [PK_ListSlot]
    PRIMARY KEY CLUSTERED ([Code] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [Route_ID] in table 'ListPackage'
ALTER TABLE [dbo].[ListPackage]
ADD CONSTRAINT [FK_PackageRoute]
    FOREIGN KEY ([Route_ID])
    REFERENCES [dbo].[ListRoute]
        ([ID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_PackageRoute'
CREATE INDEX [IX_FK_PackageRoute]
ON [dbo].[ListPackage]
    ([Route_ID]);
GO

-- Creating foreign key on [Gdz_ID] in table 'ListVehicle'
ALTER TABLE [dbo].[ListVehicle]
ADD CONSTRAINT [FK_VehicleGdz]
    FOREIGN KEY ([Gdz_ID])
    REFERENCES [dbo].[ListGdz]
        ([ID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_VehicleGdz'
CREATE INDEX [IX_FK_VehicleGdz]
ON [dbo].[ListVehicle]
    ([Gdz_ID]);
GO

-- Creating foreign key on [Slot_Code] in table 'ListVehicle'
ALTER TABLE [dbo].[ListVehicle]
ADD CONSTRAINT [FK_VehicleSlot]
    FOREIGN KEY ([Slot_Code])
    REFERENCES [dbo].[ListSlot]
        ([Code])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_VehicleSlot'
CREATE INDEX [IX_FK_VehicleSlot]
ON [dbo].[ListVehicle]
    ([Slot_Code]);
GO

-- Creating foreign key on [Center_Code] in table 'ListSlot'
ALTER TABLE [dbo].[ListSlot]
ADD CONSTRAINT [FK_SlotCenter]
    FOREIGN KEY ([Center_Code])
    REFERENCES [dbo].[ListCenter]
        ([Code])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_SlotCenter'
CREATE INDEX [IX_FK_SlotCenter]
ON [dbo].[ListSlot]
    ([Center_Code]);
GO

-- Creating foreign key on [Center_Code] in table 'ListVehicle'
ALTER TABLE [dbo].[ListVehicle]
ADD CONSTRAINT [FK_VehicleCenter]
    FOREIGN KEY ([Center_Code])
    REFERENCES [dbo].[ListCenter]
        ([Code])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_VehicleCenter'
CREATE INDEX [IX_FK_VehicleCenter]
ON [dbo].[ListVehicle]
    ([Center_Code]);
GO

-- Creating foreign key on [Slot_Code] in table 'ListPackage'
ALTER TABLE [dbo].[ListPackage]
ADD CONSTRAINT [FK_SlotPackage]
    FOREIGN KEY ([Slot_Code])
    REFERENCES [dbo].[ListSlot]
        ([Code])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_SlotPackage'
CREATE INDEX [IX_FK_SlotPackage]
ON [dbo].[ListPackage]
    ([Slot_Code]);
GO

-- Creating foreign key on [Route_ID] in table 'ListStage'
ALTER TABLE [dbo].[ListStage]
ADD CONSTRAINT [FK_RouteStage]
    FOREIGN KEY ([Route_ID])
    REFERENCES [dbo].[ListRoute]
        ([ID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_RouteStage'
CREATE INDEX [IX_FK_RouteStage]
ON [dbo].[ListStage]
    ([Route_ID]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------