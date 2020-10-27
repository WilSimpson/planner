export class AppUser {
  constructor(private _username: string,
              private _password: string,
              private _email: string,
              private _roles: AppUserRole[]) { }

  get username(): string {
    return this._username;
  }

  get password(): string {
    return this._password;
  }

  get email(): string {
    return this._email;
  }

  public getPrimaryStringRole(): string {
    if (this._roles.length <= 0) {
      return new AppUserRole(EAppUserRole.PUBLIC).toString();
    }
    return this._roles[0].toString();
  }

  get roles(): AppUserRole[] {
    return this._roles;
  }
}

export class AppUserRole {
  constructor(private _role: EAppUserRole) { }

  get role(): EAppUserRole {
    return this._role;
  }

  public toString(): string {
    return 'test';
  }
}

export enum EAppUserRole {
  PUBLIC,
  USER,
  ADMIN
}

export interface UserConfig {
  username: string;
  password: string;
}
