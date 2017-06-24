<xsl:template match="/carrental">
    <html>
        <head><title>RENTALS</title></head>
        <body>
            <xsl:apply-templates select="rental"/>
        </body>
    </html>
</xsl:template>
<xsl:template match="rental">
    <table border="0">
        <h1>MAKE=<xsl:value-of select="make"/></h1><br />
        <h1>model=<xsl:value-of select="model"/></h1><br />
        <h1>start=<xsl:value-of select="start"/></h1><br />
        <h1>end=<xsl:value-of select="end"/></h1><br />
    </table>
</xsl:template>
