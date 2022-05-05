package boblovespihonkaimod.cards.powers;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.ShockResonancePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ShockResonance extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("shockResonance");
	public static final String IMG = DefaultMod.makeCardPath("Power.png");
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	// STAT DECLARATION
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.POWER;
	private static final int COST = 1;
	private static final int MAGIC = 7;
	private static final int UPGRADE_COST = -1;

	public ShockResonance()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
	}

	@Override
	public void use(AbstractPlayer player, AbstractMonster abstractMonster)
	{
		addToBot(new ApplyPowerAction(player, player, new ShockResonancePower(player, player, 0)));
	}

	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeBaseCost(COST + UPGRADE_COST);
		}
	}
}
