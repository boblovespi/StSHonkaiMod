package boblovespihonkaimod.util;

import com.megacrit.cardcrawl.monsters.AbstractMonster.Intent;

public class IntentPredicates
{
	public static boolean isAttacking(Intent intent)
	{
		return intent == Intent.ATTACK || intent == Intent.ATTACK_BUFF || intent == Intent.ATTACK_DEBUFF || intent == Intent.ATTACK_DEFEND;
	}
}
