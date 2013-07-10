CREATE TABLE [dbo].[ListSlot] (
    [Code]         INT IDENTITY (1, 1) NOT NULL,
    [MaxCapacity]  INT NOT NULL,
    [FreeCapacity] INT NOT NULL,
    [Center_Code]  INT NULL,
    CONSTRAINT [PK_ListSlot] PRIMARY KEY CLUSTERED ([Code] ASC)
);


GO
