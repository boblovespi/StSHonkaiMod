package boblovespihonkaimod.cards.skills;

import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.XCostAction;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HonkaiCascade extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("honkaiCascade");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = -1;
	private static final int DRAW = 1;
	private static final int UPGRADE_DRAW = 0;
	private static final int ENERGY = 0;
	private static final int ENERGY_UPGRADE = 1;
	private static final int VOIDS = 2;

	public HonkaiCascade()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		// baseDraw = draw = DRAW;
		magicNumber = baseMagicNumber = ENERGY;
		// defaultBaseSecondMagicNumber = defaultSecondMagicNumber = VOIDS;
		// exhaust = true;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			// baseDraw += UPGRADE_DRAW;
			// draw = baseDraw;
			upgradeMagicNumber(ENERGY_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		int honkai = 0;
		if (AbstractDungeon.player.hasPower(HonkaiPower.id))
			honkai = AbstractDungeon.player.getPower(HonkaiPower.id).amount;
		addToBot(new XCostAction(this, (n, params) -> {
			for (int i = 0; i < n + params[0]; i++)
			{
				addToBot(new ApplyPowerAction(p, p, new HonkaiPower(p, p, params[1])));
			}
			return true;
		}, magicNumber, honkai));
	}
}
