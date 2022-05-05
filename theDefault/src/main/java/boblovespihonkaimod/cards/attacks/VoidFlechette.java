package boblovespihonkaimod.cards.attacks;

import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import boblovespihonkaimod.actions.StunDamageAction;
import boblovespihonkaimod.cards.AbstractDynamicCard;
import boblovespihonkaimod.characters.TheDefault;
import boblovespihonkaimod.util.FreeIfCertainCostMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;

public class VoidFlechette extends AbstractDynamicCard
{
	public static final String ID = DefaultMod.makeID("voidFlechette");
	public static final String IMG = DefaultMod.makeCardPath("Attack.png");

	// STAT DECLARATION
	public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 3;
	private static final int DAMAGE = 7;
	private static final int DAMAGE_UPGRADE = 2;
	private static final int MAGIC = 3;
	private static final int UPGRADE = 0;

	// /STAT DECLARATION/


	public VoidFlechette()
	{
		super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
		baseDamage = DAMAGE;
		magicNumber = baseMagicNumber = MAGIC;
		CardModifierManager.addModifier(this, new FreeIfCertainCostMod(12, COST));
	}

	//Upgraded stats.
	@Override
	public void upgrade()
	{
		if (!upgraded)
		{
			upgradeName();
			upgradeDamage(DAMAGE_UPGRADE);
			upgradeMagicNumber(UPGRADE);
			initializeDescription();
		}
	}

	// Actions the card should do.
	@Override
	public void use(AbstractPlayer p, AbstractMonster m)
	{
		for (int i = 0; i < magicNumber; i++)
		{
			addToBot(new VFXAction(new ThrowDaggerEffect(m.hb_x, m.hb_y)));
			addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		}
	}
}
