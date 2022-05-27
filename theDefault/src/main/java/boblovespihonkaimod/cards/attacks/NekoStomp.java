package boblovespihonkaimod.cards.attacks;

import basemod.abstracts.DynamicVariable;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class NekoStomp extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("nekoStomp");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 2;
	private static final int DAMAGE = 13;
	private static final int DAMAGE_IF_FIRST_MULT = 2;
	private static final int UPGRADE = 5;

	// /STAT DECLARATION/


	public NekoStomp()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeDamage(UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		if (m != null)
			addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
		if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= 1)
			addToBot(new DamageAction(m, new DamageInfo(p, DAMAGE_IF_FIRST_MULT * damage, damageTypeForTurn), AttackEffect.NONE));
		else
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AttackEffect.NONE));
	}

	public static class Var extends DynamicVariable
	{   // Custom Dynamic Variables are what you do if you need your card text to display a cool, changing number that the base game doesn't provide.
		// If the !D! and !B! (for Damage and Block) etc. are not enough for you, this is how you make your own one. It Changes In Real Time!


		// This is what you type in your card string to make the variable show up. Remember to encase it in "!"'s in the json!
		@Override
		public String key()
		{
			return DefaultMod.makeID("nekoStompD");
		}

		// Checks whether the current value is different than the base one.
		// For example, this will check whether your damage is modified (i.e. by strength) and color the variable appropriately (Green/Red).
		@Override
		public boolean isModified(AbstractCard card)
		{
			return card.isDamageModified;
		}

		// The value the variable should display.
		@Override
		public int value(AbstractCard card)
		{
			return card.damage * DAMAGE_IF_FIRST_MULT;
		}

		// The baseValue the variable should display.
		@Override
		public int baseValue(AbstractCard card)
		{
			return card.baseDamage * DAMAGE_IF_FIRST_MULT;
		}

		// If the card has it's damage upgraded, this variable will glow green on the upgrade selection screen as well.
		@Override
		public boolean upgraded(AbstractCard card)
		{
			return card.upgradedDamage;
		}
	}
}
