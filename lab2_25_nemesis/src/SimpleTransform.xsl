<xsl:transform version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" ...>
<!-- NewItem variable set by WS-BPEL process;
defaults to empty item -->
    <xsl:param name="NewItem">
        <foo:itemElement />
    </xsl:param>

    <xsl:template match="foo:itemElement"> <!-- line 1 -->
        <xsl:copy-of select="." /> <!-- line 2 -->
        <xsl:if test="position()=last()"> <!-- line 3 -->
            <xsl:copy-of select="$NewItem" /> <!-- line 4 -->
        </xsl:if> <!-- line 5 -->
    </xsl:template> <!-- line 6 -->
</xsl:transform>