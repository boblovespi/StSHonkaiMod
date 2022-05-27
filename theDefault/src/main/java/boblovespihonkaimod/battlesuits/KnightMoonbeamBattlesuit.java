package boblovespihonkaimod.battlesuits;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import boblovespihonkaimod.DefaultMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;

public class KnightMoonbeamBattlesuit extends AbstractBattlesuit
{
	// multis do 1x more multi
	// for attacks and blocks

	public static String ID = DefaultMod.makeID("kmb");
	private static final StanceStrings strings = CardCrawlGame.languagePack.getStanceString(ID);

	public KnightMoonbeamBattlesuit()
	{
		super.ID = ID;
		name = strings.NAME;
	}

	@Override
	public void onUltUse()
	{
		// todo: figure out
	}

	@Override
	public void updateDescription()
	{
		description = strings.DESCRIPTION[0];
	}

	@Override
	public void onPlayCard(AbstractCard card)
	{
		super.onPlayCard(card);
	}

	@Override
	public void onEnterStance()
	{

	}

	@Override
	public void onExitOrVictory()
	{
	}

	@Override
	public void update()
	{
		super.update();
	}

	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card)
	{
		return damage;
	}
}
