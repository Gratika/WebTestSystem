import { defineStore } from "pinia";
const baseUrl = `${import.meta.env.VITE_API_URL}`;
export const useUserStore = defineStore("user", {
    state: () => ({
        user: null,
    }),

    actions: {
        async fetchUser() {
            const res = await fetch("baseUrl/user");

            const user = await res.json();
            this.user = user;
        },
        async signUp(email, password) {
            const res = await fetch("baseUrl/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });
            const user = await res.json()
            this.user = user;
        },
        async signIn(email, password) {
            const res = await fetch("baseUrl/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email, password }),
            });
            const user = await res.json();
            this.user = user;
        },
    },
});