package boblovespihonkaimod.cards.powers;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.CPTSymmetryPower;
import boblovespihonkaimod.powers.SouliumReactorPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChargeParticleTimeSymmetry extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("cptSymmetry");
	public static final String IMG = DefaultMod.makeCardPath("Power.png");
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	// STAT DECLARATION
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int MAGIC = 1;
	private static final int MAGIC_UPGRADE = 0;

	public ChargeParticleTimeSymmetry()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		magicNumber = baseMagicNumber = MAGIC;
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster abstractMonster)
	{
		addToBot(new ApplyPowerAction(player, player, new CPTSymmetryPower(player, player, magicNumber)));
	}

	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(MAGIC_UPGRADE);
			isInnate = true;
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		}
	}
}
