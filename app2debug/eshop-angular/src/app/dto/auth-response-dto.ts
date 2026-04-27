export class AuthResponseDto {
    constructor(private _token: string, private _success: boolean) { }

    public get token(): string {
        return this._token;
    }

    public set token(value: string) {
        this._token = value;
    }

    public get success(): boolean {
        return this._success;
    }

    public set success(value: boolean) {
        this._success = value;
    }
}
