<xsl:transform version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns4="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping">
<!-- NewItem variable set by WS-BPEL process;
defaults to empty item -->
    <xsl:param name="NewItem">
        <item />
    </xsl:param>

    <xsl:template match="item"> <!-- line 1 -->
        <xsl:copy-of select="." /> <!-- line 2 -->
        <xsl:if test="position()=last()"> <!-- line 3 -->
            <xsl:copy-of select="$NewItem" /> <!-- line 4 -->
        </xsl:if> <!-- line 5 -->
    </xsl:template> <!-- line 6 -->
</xsl:transform>