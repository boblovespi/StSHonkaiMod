package boblovespihonkaimod.cards.attacks;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;

public class GyrodriveKata extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("gyrodriveKata");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int DAMAGE = 3;
	private static final int MULTIS = 2;
	private static final int UPGRADE_MULTIS = 1;

	public GyrodriveKata()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		magicNumber = baseMagicNumber = MULTIS;
		isMultiDamage = true;
		CardModifierManager.addModifier(this, new ClawLikeMod(2, magicNumber));
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeMagicNumber(UPGRADE_MULTIS);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		for (int i = 0; i < magicNumber; i++)
		{
			addToBot(new VFXAction(new ImpactSparkEffect(m.hb.cX, m.hb.cY)));
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
		}
	}

	public static class ClawLikeMod extends AbstractCardModifier
	{
		public static String ID = DefaultMod.makeID("clawLikeMod");
		private int turnsMax;
		private int turns;
		private int baseAttacks;
		private int attacks;
		private AbstractCreature target = null;

		public ClawLikeMod(int turnsMax, int baseAttacks)
		{
			this.turnsMax = turnsMax;
			this.baseAttacks = baseAttacks;
			turns = turnsMax;
			attacks = baseAttacks;
		}

		@Override
		public boolean isInherent(AbstractCard card)
		{
			return true;
		}

		@Override
		public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo)
		{
			if (mo == target)
			{
				card.magicNumber = attacks;
				card.isMagicNumberModified = true;
			} else
			{
				card.magicNumber = baseAttacks;
				card.isMagicNumberModified = false;
			}
		}

		@Override
		public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action)
		{
			if (target == this.target)
			{
				card.magicNumber = attacks;
				card.isMagicNumberModified = true;
				attacks++;
				turns = turnsMax;
			} else
			{
				card.magicNumber = baseAttacks;
				card.isMagicNumberModified = false;
				this.target = target;
				attacks++;
				turns = turnsMax;
			}
		}

		@Override
		public void onInitialApplication(AbstractCard card)
		{
			baseAttacks = card.baseMagicNumber;
			attacks = baseAttacks;
		}

		@Override
		public void atEndOfTurn(AbstractCard card, CardGroup group)
		{
			if (attacks == baseAttacks)
				return;
			turns--;
			if (turns < 0)
			{
				attacks = baseAttacks;
				target = null;
				turns = turnsMax;
			}
		}

		@Override
		public AbstractCardModifier makeCopy()
		{
			return new ClawLikeMod(turnsMax, baseAttacks);
		}
	}

}
