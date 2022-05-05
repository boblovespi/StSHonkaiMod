package boblovespihonkaimod.cards.skills;

import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.ExhaustVoidEnergyAction;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.powers.HonkaiPower;
import boblovespihonkaimod.util.FreeIfCertainCostMod;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SubspacePocket extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("subspacePocket");
	public static final String IMG = DefaultMod.makeCardPath("Skill.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	private static final int COST = 1;
	private static final int DRAW = 3;
	private static final int UPGRADE_DRAW = 1;
	private static final int EXHAUST = 2;
	private static final int EXHAUST_UPGRADE = 1;

	public SubspacePocket()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDraw = draw = DRAW;
		magicNumber = baseMagicNumber = EXHAUST;
		exhaust = true;
		CardModifierManager.addModifier(this, new FreeIfCertainCostMod(10, COST));
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			baseDraw += UPGRADE_DRAW;
			draw = baseDraw;
			upgradeMagicNumber(EXHAUST_UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		addToBot(new DrawCardAction(draw));
		addToBot(new ExhaustVoidEnergyAction(p, magicNumber));
	}
}
