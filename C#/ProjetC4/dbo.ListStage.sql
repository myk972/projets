CREATE TABLE [dbo].[ListStage] (
    [ID]                 INT            IDENTITY (1, 1) NOT NULL,
    [Type]               NVARCHAR (MAX) NOT NULL,
    [DatePackageAtStage] DATE           NULL,
    CONSTRAINT [PK_ListStage] PRIMARY KEY CLUSTERED ([ID] ASC)
);


GO
