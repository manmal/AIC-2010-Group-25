<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns4="http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping">
<!-- NewItem variable set by WS-BPEL process; -->
    <xsl:param name="NewItemProductID">X</xsl:param>
    <xsl:param name="NewItemQuantity">Y</xsl:param>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="item">
        <xsl:copy-of select="." />
        <xsl:if test="position()=last()-1">
            <item>
                <quantity><xsl:copy-of select="$NewItemQuantity" /></quantity>
                <product xmlns:msgns="http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse" id="{$NewItemProductID}"/>
            </item>
        </xsl:if>
    </xsl:template>
</xsl:transform>